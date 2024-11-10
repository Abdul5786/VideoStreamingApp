import { useState } from 'react'
import './App.css'
import VideoUpload from './components/VideoUpload'

function App() {
  const [count, setCount] = useState(0)

  return (
    <> 
    <div className='flex flex-col items-center space-y-9 justify-center py-3'>
     <h1 className="text-4xl font font-extrabold text-gray-200">
     Syed Video Streaming APP
    </h1>
    
     <VideoUpload/>
    </div>
    </>
  )
}

export default App
