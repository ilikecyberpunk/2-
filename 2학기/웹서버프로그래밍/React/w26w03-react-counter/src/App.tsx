import './App.css'
import { useState } from 'react';

function App(){

  const [count, setCount] = useState(0);
  // const plusOne = (prev: number) => prev +1;
  
  return (
    <>
      <h1>{count}</h1>
      <button onClick={()=>{
        setCount(A => A+5)
      } }>
        증가
      </button>
    </>
  )
}

export default App

// 동기 처리 방식은 한줄한줄 하나씩 하는데, 비동기는 하나로 모아서 일정 시간 뒤에 처리


