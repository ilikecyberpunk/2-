const FOCUS_TIME = 25 * 60; // 25분
const REST_TIME = 5 * 60;  // 5분

let remainingSeconds = FOCUS_TIME;
let isRunning = false;
let isFocusMode = true;
let timerInterval = null;

const timerDisplay = document.getElementById("timerDisplay");
const modeLabel = document.getElementById("modeLabel");
const startPauseBtn = document.getElementById("startPauseBtn");
const resetBtn = document.getElementById("resetBtn");
const switchModeBtn = document.getElementById("switchModeBtn");
const timerCard = document.querySelector(".timer-card");

function formatTime(seconds) {
  const mins = Math.floor(seconds / 60);
  const secs = seconds % 60;
  return `${String(mins).padStart(2, "0")}:${String(secs).padStart(2, "0")}`;
}

function updateDisplay() {
  timerDisplay.textContent = formatTime(remainingSeconds);
  modeLabel.textContent = isFocusMode ? "집중 시간" : "휴식 시간";

  if (isFocusMode) {
    timerCard.classList.remove("rest-mode");
  } else {
    timerCard.classList.add("rest-mode");
  }
}

function startTimer() {
  if (isRunning) return;

  isRunning = true;
  startPauseBtn.textContent = "일시정지";

  timerInterval = setInterval(() => {
    remainingSeconds--;

    if (remainingSeconds < 0) {
      clearInterval(timerInterval);
      isRunning = false;
      startPauseBtn.textContent = "시작";

      // 시간이 끝나면 모드 자동 전환
      isFocusMode = !isFocusMode;
      remainingSeconds = isFocusMode ? FOCUS_TIME : REST_TIME;
      updateDisplay();

      // 간단한 알림
      alert(isFocusMode ? "휴식 끝! 다시 집중하세요 🔥" : "집중 끝! 잠시 쉬세요 ☕");
      return;
    }

    updateDisplay();
  }, 1000);
}

function pauseTimer() {
  if (!isRunning) return;

  isRunning = false;
  clearInterval(timerInterval);
  startPauseBtn.textContent = "시작";
}

function toggleStartPause() {
  if (isRunning) {
    pauseTimer();
  } else {
    startTimer();
  }
}

function resetTimer() {
  pauseTimer();
  remainingSeconds = isFocusMode ? FOCUS_TIME : REST_TIME;
  updateDisplay();
}

function switchMode() {
  pauseTimer();
  isFocusMode = !isFocusMode;
  remainingSeconds = isFocusMode ? FOCUS_TIME : REST_TIME;
  updateDisplay();
}

// 이벤트 연결
startPauseBtn.addEventListener("click", toggleStartPause);
resetBtn.addEventListener("click", resetTimer);
switchModeBtn.addEventListener("click", switchMode);

// 초기 화면 세팅
updateDisplay();
