import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import UserModal from './modules/user/UserModal.jsx';

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <div className="App">
      <UserModal />
    </div>
    </>
  )
}

export default App
