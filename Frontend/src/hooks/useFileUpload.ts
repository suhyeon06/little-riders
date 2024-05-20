import React, { useState } from 'react'

export default function useFileUpload() {
  // Url로 변환한 이미지 파일
  const [uploadImgUrl, setUploadImgUrl] = useState<string>('')
  //   이미지 파일
  const [uploadFile, setUploadFile] = useState<File | null>(null)

  const onChangeImageUpload = (e: React.ChangeEvent<HTMLInputElement>) => {
    const currentFile = (e.target.files as FileList)[0]
    setUploadFile(currentFile)
    const reader = new FileReader()
    reader.readAsDataURL(currentFile)
    reader.onloadend = () => {
      setUploadImgUrl(reader.result as string)
    }
  }
  return { uploadImgUrl, onChangeImageUpload, uploadFile }
}
