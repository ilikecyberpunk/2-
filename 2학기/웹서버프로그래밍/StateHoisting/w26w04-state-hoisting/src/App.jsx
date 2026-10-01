import './App.css'
import { useState } from 'react'

function App() {
  const [count, setCount] = useState(0)

  return (
    <div>
      <Counter
        count={count}
        onIncrement = { ()=> setCount(prev => prev+1) }
      />
    </div>
  )
}

function Counter(props) {

  return (
    <div>
      <h1>Counter: {props.count}</h1>
      <button 
        onClick={ onIncrement }>
          증가
      </button>
    </div>
  )
}

export default App