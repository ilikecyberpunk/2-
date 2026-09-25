import { useEffect, useState } from "react";
import "./App.css";

type Mode = "focus" | "break";

const FOCUS_TIME = 25 * 60;
const BREAK_TIME = 5 * 60;

function App() {
  const [mode, setMode] = useState<Mode>("focus");
  const [seconds, setSeconds] = useState(FOCUS_TIME);
  const [running, setRunning] = useState(false);
  const [completedSessions, setCompletedSessions] = useState(0);

  useEffect(() => {
    if (!running) return;

    const timer = setInterval(() => {
      setSeconds((prev) => {
        if (prev <= 1) {
          setRunning(false);

          if (mode === "focus") {
            setCompletedSessions((count) => count + 1);
            setMode("break");
            return BREAK_TIME;
          } else {
            setMode("focus");
            return FOCUS_TIME;
          }
        }

        return prev - 1;
      });
    }, 1000);

    return () => clearInterval(timer);
  }, [running, mode]);

  const changeMode = (nextMode: Mode) => {
    setMode(nextMode);
    setRunning(false);
    setSeconds(nextMode === "focus" ? FOCUS_TIME : BREAK_TIME);
  };

  const resetTimer = () => {
    setRunning(false);
    setSeconds(mode === "focus" ? FOCUS_TIME : BREAK_TIME);
  };

  const minutes = String(Math.floor(seconds / 60)).padStart(2, "0");
  const secs = String(seconds % 60).padStart(2, "0");

  return (
    <main className="app">
      <section className="timer-card">
        <p className="eyebrow">POMODORO TIMER</p>
        <h1>뽀모도로 타이머</h1>

        <div className="mode-buttons">
          <button
            className={mode === "focus" ? "mode active" : "mode"}
            onClick={() => changeMode("focus")}
          >
            집중
          </button>
          <button
            className={mode === "break" ? "mode active" : "mode"}
            onClick={() => changeMode("break")}
          >
            휴식
          </button>
        </div>

        <div className="time">
          {minutes}:{secs}
        </div>

        <div className="control-buttons">
          <button className="primary" onClick={() => setRunning(!running)}>
            {running ? "일시정지" : "시작"}
          </button>
          <button className="secondary" onClick={resetTimer}>
            초기화
          </button>
        </div>

        <p className="session-count">
          완료한 집중 세션: <strong>{completedSessions}회</strong>
        </p>

        <button
          className="record-button"
          onClick={() =>
            alert(`현재까지 집중 세션을 ${completedSessions}회 완료했습니다.`)
          }
        >
          집중 세션 완료 기록
        </button>
      </section>
    </main>
  );
}

export default App;
