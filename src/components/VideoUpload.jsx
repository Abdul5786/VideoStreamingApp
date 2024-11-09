import React from 'react'
import videoLogo from '../assets/video-posting.png'
import { Button, Card } from 'flowbite-react'
const VideoUpload = () => {
  return (
    <div className='text-white'>
      <Card>
        <h1>UPLOAD VIDEO</h1>
        <form class="flex items-center space-x-6">
  <div class="shrink-0">
    <img class="h-16 w-16 object-cover " src={videoLogo} alt="Current profile photo" />
  </div>
  <label class="block">
    <span class="sr-only">Choose profile photo</span>
    <input type="file" class="block w-full text-sm text-slate-500
      file:mr-4 file:py-2 file:px-4
      file:rounded-full file:border-0
      file:text-sm file:font-semibold
      file:bg-violet-50 file:text-violet-700
      hover:file:bg-violet-100
    "/>
  </label>
</form>
       <div className='flex justify-center'>
        <Button>Upload</Button>
       </div>
      </Card>
    </div>
  )
}

export default VideoUpload
