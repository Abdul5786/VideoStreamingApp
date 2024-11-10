import React, { useState } from 'react';
import videoLogo from '../assets/video-posting.png';
import { Button, Card, Label, Progress, Textarea, TextInput } from 'flowbite-react';
import axios from 'axios';

const VideoUpload = () => {
    const [selectedFile, setSelectedFile] = useState(null);
    const [progress, setProgress] = useState(0);
    const [uploading, setUploading] = useState(false);
    const [message, setMessage] = useState("");
    const [meta, setMeta] = useState({
        title: "",
        desc: ""
    });

    function handleFilechange(event) {
        console.log(event.target.files);
        setSelectedFile(event.target.files[0]);
    }

    function formFieldChange(event) {
        console.log(event.target.name);
        console.log(event.target.value);
        setMeta({
            ...meta,
            [event.target.name]: event.target.value,
        });
    }

    function handleForm(formEvent) {
        formEvent.preventDefault();
        console.log(formEvent);
        console.log(meta);
        if (!selectedFile) {
            alert("Please select a file!");
            return;
        }

        saveVideoToServer(selectedFile, meta);
    }

    async function saveVideoToServer(video, videoMetadata) {
        setUploading(true);

        try {
            let formData = new FormData();
            formData.append("title", videoMetadata.title);
            formData.append("description", videoMetadata.desc);
            formData.append("file", video);
         
            let response = await axios.post('http://localhost:8080/api/v1/videos/add', formData, {
                headers: {
                    "Content-Type": "multipart/form-data"
                },
                onUploadProgress: (progressEvent) => {
                    const progress= Math.round((progressEvent.loaded * 100) / progressEvent.total);
                    setProgress((progress));
                   
                },
            });

            setMessage("File uploaded successfully");
            setUploading(false);
        } catch (error) {
            console.log(error);
            setMessage("Error uploading file");
            setUploading(false);
        } finally {
            setUploading(false);
        }
    }

    return (
        <div className='text-white'>
            <Card>
                <h1>UPLOAD VIDEO</h1>
                
                <div>
                    <form noValidate className="space-y-7" onSubmit={handleForm}>
                        <div>
                            <div className="mb-2 block">
                                <Label htmlFor="file-upload" value="Video Title" />
                            </div>
                            <TextInput onChange={formFieldChange} name="title" placeholder='Enter video title' />
                        </div>

                        <div className="max-w-md">
                            <div className="mb-2 block">
                                <Label htmlFor="comment" value="Video Description" />
                            </div>
                            <Textarea onChange={formFieldChange} name="desc" id="comment" placeholder="Write a video description..." required rows={4} />
                        </div>
                          
                        <div className='flex items-center space-x-5 justify-center'>
                            <div className="shrink-0">
                                <img className="h-16 w-16 object-cover" src={videoLogo} alt="Current profile photo" />
                            </div>
                            <label className="block">
                                <span className="sr-only">Choose video file</span>
                                <input 
                                    name="file" 
                                    onChange={handleFilechange} 
                                    type="file" 
                                    className="block w-full text-sm text-slate-500
                                        file:mr-4 file:py-2 file:px-4
                                        file:rounded-full file:border-0
                                        file:text-sm file:font-semibold
                                        file:bg-violet-50 file:text-violet-700
                                        hover:file:bg-violet-100"
                                />
                            </label>
                        </div>


                       <div>
                        <Progress hidden={uploading} progress={progress} textLabel="uploading" size="lg" labelProgress labelText/>

                       </div>

                        <div className='flex justify-center'>
                            <Button type='submit'>Submit</Button>
                        </div>
                    </form>

                    {uploading && <p>Uploading: {progress}%</p>}
                    {message && <p>{message}</p>}
                </div>
            </Card>
        </div>
    );
}

export default VideoUpload;
