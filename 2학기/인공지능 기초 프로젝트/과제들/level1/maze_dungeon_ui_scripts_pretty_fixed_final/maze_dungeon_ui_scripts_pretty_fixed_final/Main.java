import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

/**
 * 미로 탈출 과제
 *
 * Java  : 게임 로직 / 파일 / BFS / DFS 미로 생성 / 저장
 * HTML  : 화면 / 아이콘 / 사용자 입력
 * JS    : Java 서버와 HTTP 통신
 *
 * 실행
 * 1. javac Main.java
 * 2. java Main
 * 3. http://localhost:8080
 */
public class Main {
    static final int SIZE = 10;
    static final int PORT = 8080;
    static final int TIME_LIMIT = 60;

    // HTML과 게임 데이터가 있는 프로젝트 폴더를 찾습니다.
    // VS Code에서 실행하더라도 현재 작업 폴더를 우선 사용합니다.
    static final Path BASE_DIR = findBaseDir();

    // 기본 미로 3개. 실행할 때 maze1.txt~maze3.txt로 생성된다.
    static final String[] DEFAULT_MAZES = {
        "##########\n" +
        "#S...K...#\n" +
        "###.###L##\n" +
        "#...#....#\n" +
        "#.#.#.##.#\n" +
        "#.#...T..#\n" +
        "#.#####.##\n" +
        "#.......E#\n" +
        "#........#\n" +
        "##########",

        "##########\n" +
        "#S....K..#\n" +
        "#.#####.##\n" +
        "#.....#.L#\n" +
        "#####.#..#\n" +
        "#T....#..#\n" +
        "#.######.#\n" +
        "#......E.#\n" +
        "#........#\n" +
        "##########",

        "##########\n" +
        "#S...#...#\n" +
        "#.##.#.K.#\n" +
        "#....#.L.#\n" +
        "###..###.#\n" +
        "#...T....#\n" +
        "#.#####.##\n" +
        "#.......E#\n" +
        "#........#\n" +
        "##########"
    };

    static Path findBaseDir() {
        Path current = Path.of("").toAbsolutePath().normalize();

        if (Files.exists(current.resolve("index.html"))) {
            return current;
        }

        try {
            Path location = Path.of(
                    Main.class.getProtectionDomain()
                            .getCodeSource()
                            .getLocation()
                            .toURI())
                    .toAbsolutePath()
                    .normalize();

            if (Files.isRegularFile(location)) {
                location = location.getParent();
            }

            Path check = location;
            for (int i = 0; i < 6 && check != null; i++) {
                if (Files.exists(check.resolve("index.html"))) {
                    return check;
                }
                check = check.getParent();
            }
        } catch (Exception ignored) {
            // 현재 작업 폴더를 마지막 후보로 사용합니다.
        }

        return current;
    }

    static Path dataPath(String name) {
        return BASE_DIR.resolve(name).normalize();
    }

    public static void main(String[] args) throws Exception {
        initFiles();
        Game game = new Game();

        HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);

        page(server, "/", "index.html");
        page(server, "/editor", "editor.html");
        page(server, "/records", "records.html");

        server.createContext("/api/start", e -> {
            Map<String, String> q = query(e.getRequestURI());
            String type = q.getOrDefault("type", "maze");
            int number = number(q.get("n"));
            boolean timed = "1".equals(q.getOrDefault("time", "1"));
            sendJson(e, game.start(type, number, timed));
        });

        server.createContext("/api/state", e -> sendJson(e, game.state()));

        server.createContext("/api/move", e -> {
            String dir = query(e.getRequestURI()).getOrDefault("dir", "");
            sendJson(e, game.move(dir));
        });

        server.createContext("/api/path", e -> sendJson(e, game.bfsJson()));
        server.createContext("/api/records", e -> sendJson(e, game.records()));
        server.createContext("/api/save", e -> sendJson(e, game.save()));
        server.createContext("/api/load", e -> sendJson(e, game.load()));
        server.createContext("/api/end", e -> sendJson(e, game.end()));
        server.createContext("/api/generate", e -> sendJson(e, game.generate()));

        server.createContext("/api/custom", e -> {
            String text = new String(
                    e.getRequestBody().readAllBytes(),
                    StandardCharsets.UTF_8);
            sendJson(e, game.custom(text));
        });

        server.start();

        System.out.println("========================================");
        System.out.println("          미로 탈출 게임 시작");
        System.out.println("          http://localhost:" + PORT);
        System.out.println("기록 파일 : " + dataPath("records.txt").toAbsolutePath());
        System.out.println("최고 기록 : " + dataPath("highscores.txt").toAbsolutePath());
        System.out.println("저장 파일 : " + dataPath("savegame.txt").toAbsolutePath());
        System.out.println("========================================");
    }

    static void page(HttpServer server, String path, String file) {
        server.createContext(path, e -> {
            try {
                byte[] data = Files.readAllBytes(dataPath(file));
                e.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
                e.sendResponseHeaders(200, data.length);
                e.getResponseBody().write(data);
                e.close();
            } catch (IOException ex) {
                e.sendResponseHeaders(404, 0);
                e.close();
            }
        });
    }

    static void initFiles() throws IOException {
        for (int i = 0; i < DEFAULT_MAZES.length; i++) {
            Path file = Path.of("maze" + (i + 1) + ".txt");
            if (!Files.exists(file)) {
                Files.writeString(file, DEFAULT_MAZES[i], StandardCharsets.UTF_8);
            }
        }

        String[] names = {
            "custom_maze.txt", "generated_maze.txt",
            "records.txt", "highscores.txt", "savegame.txt"
        };

        for (String name : names) {
            Path file = dataPath(name);
            if (!Files.exists(file)) {
                Files.writeString(file, "", StandardCharsets.UTF_8);
            }
        }
    }

    static Map<String, String> query(URI uri) {
        Map<String, String> result = new HashMap<>();
        String raw = uri.getRawQuery();
        if (raw == null || raw.isEmpty()) return result;

        for (String item : raw.split("&")) {
            String[] pair = item.split("=", 2);
            String key = URLDecoder.decode(pair[0], StandardCharsets.UTF_8);
            String value = pair.length == 2
                    ? URLDecoder.decode(pair[1], StandardCharsets.UTF_8)
                    : "";
            result.put(key, value);
        }
        return result;
    }

    static int number(String value) {
        try { return Integer.parseInt(value); }
        catch (Exception e) { return 1; }
    }

    static void sendJson(HttpExchange e, String json) throws IOException {
        byte[] data = json.getBytes(StandardCharsets.UTF_8);
        e.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
        e.sendResponseHeaders(200, data.length);
        e.getResponseBody().write(data);
        e.close();
    }

    static String quote(String value) {
        if (value == null) value = "";
        return "\"" + value.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r") + "\"";
    }

    static String jsonArray(List<String> list) {
        StringBuilder b = new StringBuilder("[");
        for (int i = 0; i < list.size(); i++) {
            if (i > 0) b.append(",");
            b.append(quote(list.get(i)));
        }
        return b.append("]").toString();
    }

    static String error(String message) {
        return "{\"error\":" + quote(message) + "}";
    }

    // =========================================================
    // 게임 클래스
    // =========================================================
    static class Game {
        char[][] map = new char[SIZE][SIZE];
        int row, col, startRow, startCol, moves;
        boolean hasKey, playing, success, timed, recorded;
        long startTime;
        String mazeName = "미로 1";
        String message = "게임을 시작하세요.";
        List<String> history = new ArrayList<>();

        String start(String type, int number, boolean timeMode) {
            try {
                timed = timeMode;

                if ("generated".equals(type)) {
                    return generate();
                }

                if ("custom".equals(type)) {
                    load(Files.readString(dataPath("custom_maze.txt"), StandardCharsets.UTF_8));
                    mazeName = "사용자 미로";
                } else {
                    number = Math.max(1, Math.min(3, number));
                    load(Files.readString(
                            Path.of("maze" + number + ".txt"),
                            StandardCharsets.UTF_8));
                    mazeName = "미로 " + number;
                }

                reset();
                message = mazeName + "을 시작했습니다.";
                return state();
            } catch (Exception e) {
                return error(e.getMessage());
            }
        }

        void reset() {
            int[] s = find('S');
            row = startRow = s[0];
            col = startCol = s[1];
            moves = 0;
            hasKey = false;
            playing = true;
            success = false;
            recorded = false;
            startTime = System.currentTimeMillis();
            history.clear();
            history.add(row + "," + col);
            autoSave();
        }

        String move(String direction) {
            checkTime();
            if (!playing) return state();

            direction = direction.toUpperCase(Locale.ROOT);
            if (!Set.of("W", "A", "S", "D").contains(direction)) {
                message = "W / A / S / D만 사용할 수 있습니다.";
                return state();
            }

            int nr = row;
            int nc = col;

            if (direction.equals("W")) nr--;
            if (direction.equals("S")) nr++;
            if (direction.equals("A")) nc--;
            if (direction.equals("D")) nc++;

            if (nr < 0 || nr >= SIZE || nc < 0 || nc >= SIZE) {
                message = "미로 밖으로 갈 수 없습니다.";
                return state();
            }

            char next = map[nr][nc];

            if (next == '#') {
                message = "벽입니다.";
                return state();
            }

            if (next == 'L' && !hasKey) {
                message = "잠긴 문입니다. 열쇠를 먼저 찾으세요.";
                return state();
            }

            row = nr;
            col = nc;
            moves++;
            history.add(row + "," + col);

            if (next == 'K') {
                hasKey = true;
                message = "열쇠를 획득했습니다!";
            }

            if (next == 'T') {
                row = startRow;
                col = startCol;
                moves += 2;
                history.add(row + "," + col);
                message = "함정! 시작 위치로 돌아갑니다. 이동 +2";
            }

            if (next == 'E') {
                playing = false;
                success = true;
                saveRecord();
                updateHighScore();
                message = "출구에 도착했습니다!";
            }

            autoSave();
            return state();
        }

        void checkTime() {
            if (playing && timed && elapsed() >= TIME_LIMIT) {
                playing = false;
                success = false;
                saveRecord();
                message = "제한 시간이 끝났습니다.";
            }
        }

        long elapsed() {
            return (System.currentTimeMillis() - startTime) / 1000;
        }

        String end() {
            if (playing) {
                playing = false;
                success = false;
                saveRecord();
            } else if (!recorded && !history.isEmpty()) {
                saveRecord();
            }

            if (message == null || !message.contains("기록")) {
                message = "게임을 종료했습니다.";
            }
            autoSave();
            return state();
        }

        // =====================================================
        // 파일 미로 읽기 / 유효성 검사
        // =====================================================
        void load(String text) throws Exception {
            String[] raw = text.replace("\r", "").split("\n");
            List<String> lines = new ArrayList<>();
            for (String line : raw) if (!line.isEmpty()) lines.add(line);

            if (lines.size() != SIZE)
                throw new Exception("미로는 정확히 10×10이어야 합니다.");

            for (String line : lines)
                if (line.length() != SIZE)
                    throw new Exception("미로는 정확히 10×10이어야 합니다.");

            for (int i = 0; i < SIZE; i++)
                map[i] = lines.get(i).toCharArray();

            validate();
        }

        void validate() throws Exception {
            int s = 0, e = 0, k = 0, l = 0;

            for (int r = 0; r < SIZE; r++) {
                for (int c = 0; c < SIZE; c++) {
                    char x = map[r][c];

                    if ("#.SEKLT".indexOf(x) < 0)
                        throw new Exception("허용되지 않는 기호: " + x);

                    if (x == 'S') s++;
                    if (x == 'E') e++;
                    if (x == 'K') k++;
                    if (x == 'L') l++;

                    if ((r == 0 || r == SIZE - 1 || c == 0 || c == SIZE - 1)
                            && x != '#') {
                        throw new Exception("미로의 외곽은 모두 벽(#)이어야 합니다.");
                    }
                }
            }

            if (s != 1) throw new Exception("S는 정확히 1개여야 합니다.");
            if (e != 1) throw new Exception("E는 정확히 1개여야 합니다.");
            if (k > 1) throw new Exception("K는 최대 1개입니다.");
            if (l > 1) throw new Exception("L은 최대 1개입니다.");
            if (l > 0 && k == 0) throw new Exception("L이 있으면 K도 있어야 합니다.");

            if (bfs().isEmpty())
                throw new Exception("S에서 E까지 갈 수 없는 미로입니다.");
        }

        String custom(String text) {
            try {
                load(text);
                Files.writeString(
                        dataPath("custom_maze.txt"),
                        text.trim() + System.lineSeparator(),
                        StandardCharsets.UTF_8);
                mazeName = "사용자 미로";
                reset();
                message = "정상적인 미로입니다. 게임에 적용했습니다.";
                return state();
            } catch (Exception e) {
                return error(e.getMessage());
            }
        }

        // =====================================================
        // BFS 최단 경로
        // 상태 = (행, 열, 열쇠 보유 여부)
        // =====================================================
        List<String> bfs() {
            int[] start = find('S');
            int[] exit = find('E');

            Queue<Node> queue = new ArrayDeque<>();
            Set<String> seen = new HashSet<>();
            Map<String, String> previous = new HashMap<>();

            Node first = new Node(start[0], start[1], false);
            queue.add(first);
            seen.add(first.id());

            Node goal = null;
            int[] dr = {-1, 1, 0, 0};
            int[] dc = {0, 0, -1, 1};

            while (!queue.isEmpty()) {
                Node current = queue.poll();

                if (current.r == exit[0] && current.c == exit[1]) {
                    goal = current;
                    break;
                }

                for (int i = 0; i < 4; i++) {
                    int nr = current.r + dr[i];
                    int nc = current.c + dc[i];

                    if (nr < 0 || nr >= SIZE || nc < 0 || nc >= SIZE)
                        continue;

                    char cell = map[nr][nc];
                    if (cell == '#') continue;
                    if (cell == 'T') continue; // 자동 경로는 함정을 피함
                    if (cell == 'L' && !current.hasKey) continue;

                    Node next = new Node(
                            nr,
                            nc,
                            current.hasKey || cell == 'K');

                    if (seen.add(next.id())) {
                        queue.add(next);
                        previous.put(next.id(), current.id());
                    }
                }
            }

            if (goal == null)
                return new ArrayList<>();

            List<String> path = new ArrayList<>();
            String current = goal.id();

            while (current != null) {
                String[] p = current.split(",");
                path.add(p[0] + "," + p[1]);

                if (current.equals(first.id())) break;
                current = previous.get(current);
            }

            Collections.reverse(path);
            return path;
        }

        String bfsJson() {
            List<String> path = bfs();
            return "{\"found\":" + !path.isEmpty()
                    + ",\"length\":" + Math.max(0, path.size() - 1)
                    + ",\"path\":" + jsonArray(path) + "}";
        }

        // =====================================================
        // 자동 미로 생성 - 랜덤 DFS
        // =====================================================
        String generate() {
            Random random = new Random();

            for (int attempt = 0; attempt < 100; attempt++) {
                char[][] generated = new char[SIZE][SIZE];
                for (char[] line : generated) Arrays.fill(line, '#');

                boolean[][] visited = new boolean[SIZE][SIZE];
                carve(generated, visited, 1, 1, random);
                generated[1][1] = 'S';

                int[] exit = farthest(generated, 1, 1);
                generated[exit[0]][exit[1]] = 'E';

                List<int[]> path = simplePath(
                        generated,
                        1, 1,
                        exit[0], exit[1]);

                if (path.size() < 8) continue;

                int[] key = path.get(path.size() / 3);
                int[] lock = path.get(path.size() * 2 / 3);

                generated[key[0]][key[1]] = 'K';
                generated[lock[0]][lock[1]] = 'L';

                Set<String> mainPath = new HashSet<>();
                for (int[] p : path)
                    mainPath.add(p[0] + "," + p[1]);

                List<int[]> empty = new ArrayList<>();
                for (int r = 1; r < SIZE - 1; r++) {
                    for (int c = 1; c < SIZE - 1; c++) {
                        if (generated[r][c] == '.'
                                && !mainPath.contains(r + "," + c)) {
                            empty.add(new int[]{r, c});
                        }
                    }
                }

                Collections.shuffle(empty, random);
                if (!empty.isEmpty()) {
                    int[] trap = empty.get(0);
                    generated[trap[0]][trap[1]] = 'T';
                }

                StringBuilder text = new StringBuilder();
                for (char[] line : generated)
                    text.append(new String(line)).append('\n');

                try {
                    load(text.toString());
                    Files.writeString(
                            dataPath("generated_maze.txt"),
                            text.toString(),
                            StandardCharsets.UTF_8);
                    mazeName = "자동 생성 미로";
                    reset();
                    message = "새로운 미로를 자동 생성했습니다.";
                    return state();
                } catch (Exception ignored) {
                }
            }

            return error("자동 미로 생성에 실패했습니다.");
        }

        void carve(
                char[][] generated,
                boolean[][] visited,
                int r, int c,
                Random random) {

            visited[r][c] = true;
            generated[r][c] = '.';

            int[][] directions = {
                    {-2, 0}, {2, 0},
                    {0, -2}, {0, 2}
            };

            List<int[]> list = Arrays.asList(directions);
            Collections.shuffle(list, random);

            for (int[] d : list) {
                int nr = r + d[0];
                int nc = c + d[1];

                if (nr <= 0 || nr >= SIZE - 1
                        || nc <= 0 || nc >= SIZE - 1
                        || visited[nr][nc]) {
                    continue;
                }

                generated[r + d[0] / 2][c + d[1] / 2] = '.';
                carve(generated, visited, nr, nc, random);
            }
        }

        int[] farthest(char[][] generated, int sr, int sc) {
            Queue<int[]> q = new ArrayDeque<>();
            Map<String,Integer> distance = new HashMap<>();

            q.add(new int[]{sr, sc});
            distance.put(sr + "," + sc, 0);

            int[] far = {sr, sc};
            int best = 0;

            int[] dr = {-1, 1, 0, 0};
            int[] dc = {0, 0, -1, 1};

            while (!q.isEmpty()) {
                int[] cur = q.poll();
                int d = distance.get(cur[0] + "," + cur[1]);

                if (d > best) {
                    best = d;
                    far = cur;
                }

                for (int i = 0; i < 4; i++) {
                    int nr = cur[0] + dr[i];
                    int nc = cur[1] + dc[i];

                    if (nr <= 0 || nr >= SIZE - 1
                            || nc <= 0 || nc >= SIZE - 1
                            || generated[nr][nc] == '#') continue;

                    String id = nr + "," + nc;
                    if (distance.containsKey(id)) continue;

                    distance.put(id, d + 1);
                    q.add(new int[]{nr, nc});
                }
            }

            return far;
        }

        List<int[]> simplePath(
                char[][] generated,
                int sr, int sc,
                int er, int ec) {

            Queue<int[]> q = new ArrayDeque<>();
            Map<String,String> prev = new HashMap<>();

            q.add(new int[]{sr, sc});
            prev.put(sr + "," + sc, null);

            int[] dr = {-1, 1, 0, 0};
            int[] dc = {0, 0, -1, 1};

            while (!q.isEmpty()) {
                int[] cur = q.poll();
                if (cur[0] == er && cur[1] == ec) break;

                for (int i = 0; i < 4; i++) {
                    int nr = cur[0] + dr[i];
                    int nc = cur[1] + dc[i];

                    if (nr <= 0 || nr >= SIZE - 1
                            || nc <= 0 || nc >= SIZE - 1
                            || generated[nr][nc] == '#') continue;

                    String id = nr + "," + nc;
                    if (prev.containsKey(id)) continue;

                    prev.put(id, cur[0] + "," + cur[1]);
                    q.add(new int[]{nr, nc});
                }
            }

            String end = er + "," + ec;
            if (!prev.containsKey(end)) return new ArrayList<>();

            List<int[]> path = new ArrayList<>();
            String cur = end;

            while (cur != null) {
                String[] p = cur.split(",");
                path.add(new int[]{
                        Integer.parseInt(p[0]),
                        Integer.parseInt(p[1])
                });
                cur = prev.get(cur);
            }

            Collections.reverse(path);
            return path;
        }

        // =====================================================
        // 저장 / 이어하기
        // =====================================================
        void autoSave() {
            try {
                StringBuilder b = new StringBuilder();

                for (char[] line : map)
                    b.append(new String(line)).append('\n');

                b.append("META\n")
                        .append(mazeName).append('\n')
                        .append(row).append('\n')
                        .append(col).append('\n')
                        .append(moves).append('\n')
                        .append(hasKey).append('\n')
                        .append(playing).append('\n')
                        .append(success).append('\n')
                        .append(timed).append('\n')
                        .append(elapsed()).append('\n')
                        .append(String.join(";", history));

                Files.writeString(
                        dataPath("savegame.txt"),
                        b.toString(),
                        StandardCharsets.UTF_8);

            } catch (IOException ignored) {
            }
        }

        String save() {
            autoSave();
            message = "게임 상태를 저장했습니다.";
            return state();
        }

        String load() {
            try {
                List<String> lines =
                        Files.readAllLines(
                                dataPath("savegame.txt"),
                                StandardCharsets.UTF_8);

                if (lines.size() < 21)
                    return error("저장된 게임이 없습니다.");

                for (int i = 0; i < SIZE; i++) {
                    if (lines.get(i).length() != SIZE)
                        return error("저장 파일이 올바르지 않습니다.");
                    map[i] = lines.get(i).toCharArray();
                }

                validate();

                int i = SIZE + 1;

                mazeName = lines.get(i++);
                row = Integer.parseInt(lines.get(i++));
                col = Integer.parseInt(lines.get(i++));
                moves = Integer.parseInt(lines.get(i++));
                hasKey = Boolean.parseBoolean(lines.get(i++));
                playing = Boolean.parseBoolean(lines.get(i++));
                success = Boolean.parseBoolean(lines.get(i++));
                timed = Boolean.parseBoolean(lines.get(i++));

                long oldElapsed = Long.parseLong(lines.get(i++));

                history.clear();
                if (i < lines.size() && !lines.get(i).isEmpty()) {
                    history.addAll(
                            Arrays.asList(
                                    lines.get(i).split(";")));
                }

                int[] s = find('S');
                startRow = s[0];
                startCol = s[1];

                startTime =
                        System.currentTimeMillis()
                                - oldElapsed * 1000;

                recorded = false;
                message = "저장된 게임을 이어왔습니다.";

                return state();

            } catch (Exception e) {
                return error("저장된 게임을 불러오지 못했습니다.");
            }
        }

        // =====================================================
        // 기록
        // =====================================================
        void saveRecord() {
            if (recorded) return;
            if (history.isEmpty()) return;

            try {
                Path file = dataPath("records.txt");
                Files.createDirectories(file.toAbsolutePath().getParent());

                String line = mazeName
                        + " | 결과=" + (success ? "성공" : "종료")
                        + " | 이동=" + moves
                        + " | 시간=" + elapsed()
                        + "초"
                        + System.lineSeparator();

                Files.writeString(
                        file,
                        line,
                        StandardCharsets.UTF_8,
                        StandardOpenOption.CREATE,
                        StandardOpenOption.APPEND);

                recorded = true;
                message = "게임 기록을 저장했습니다: records.txt";
            } catch (IOException e) {
                message = "기록 저장 실패: " + e.getMessage();
            }
        }

        void updateHighScore() {
            try {
                Map<String,Integer> best =
                        new LinkedHashMap<>();

                Path file = dataPath("highscores.txt");

                if (Files.exists(file)) {
                    for (String line :
                            Files.readAllLines(file, StandardCharsets.UTF_8)) {
                        String[] p = line.split("=", 2);
                        if (p.length == 2) {
                            best.put(
                                    p[0],
                                    Integer.parseInt(p[1]));
                        }
                    }
                }

                int old = best.getOrDefault(
                        mazeName,
                        Integer.MAX_VALUE);

                best.put(
                        mazeName,
                        Math.min(old, moves));

                List<String> output = new ArrayList<>();

                for (var e : best.entrySet())
                    output.add(e.getKey() + "=" + e.getValue());

                Files.writeString(
                        file,
                        String.join("\n", output) + "\n",
                        StandardCharsets.UTF_8);

            } catch (Exception ignored) {
            }
        }

        String records() {
            try {
                return "{\"highscores\":"
                        + jsonArray(Files.readAllLines(
                                dataPath("highscores.txt"),
                                StandardCharsets.UTF_8))
                        + ",\"records\":"
                        + jsonArray(Files.readAllLines(
                                dataPath("records.txt"),
                                StandardCharsets.UTF_8))
                        + "}";
            } catch (Exception e) {
                return "{\"highscores\":[],\"records\":[]}";
            }
        }

        // =====================================================
        // 상태 JSON
        // =====================================================
        String state() {
            checkTime();

            List<String> lines = new ArrayList<>();
            for (char[] line : map)
                lines.add(new String(line));

            return "{"
                    + "\"maze\":" + quote(mazeName)
                    + ",\"player\":{\"row\":" + row
                    + ",\"col\":" + col + "}"
                    + ",\"moves\":" + moves
                    + ",\"key\":" + hasKey
                    + ",\"playing\":" + playing
                    + ",\"success\":" + success
                    + ",\"timed\":" + timed
                    + ",\"elapsed\":" + elapsed()
                    + ",\"visited\":" + jsonArray(history)
                    + ",\"map\":" + jsonArray(lines)
                    + ",\"message\":" + quote(message)
                    + "}";
        }

        int[] find(char target) {
            for (int r = 0; r < SIZE; r++)
                for (int c = 0; c < SIZE; c++)
                    if (map[r][c] == target)
                        return new int[]{r, c};
            return new int[]{-1, -1};
        }
    }

    static class Node {
        int r, c;
        boolean hasKey;

        Node(int r, int c, boolean hasKey) {
            this.r = r;
            this.c = c;
            this.hasKey = hasKey;
        }

        String id() {
            return r + "," + c + "," + hasKey;
        }
    }
}
