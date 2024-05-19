import Swal, { SweetAlertOptions } from 'sweetalert2'

type ChildInfo = {
  image: string
  name: string
  phoneNumber: string
}

export const showChildInfoAlert = (childInfo: ChildInfo) => {
  console.log(childInfo.phoneNumber)
  const html = `<div class='w-full h-full p-4 flex justify-between'>
  <img src='${childInfo.image || '/default.png'}' class='w-1/3 aspect-square object-cover'/>
    <div class='flex flex-col w-full h-full items-center justify-center gap-2'>
      <p class='text-4xl'>${childInfo.name}</p>
      <p class='text-xl'>${childInfo.phoneNumber}</p>
    </div>
  </div>`

  return Swal.fire({
    icon: undefined,
    confirmButtonText: '확인',
    html,
  })
}

export const showErrorAlert = (optionList: SweetAlertOptions) => {
  const defaultOptionList: SweetAlertOptions = {
    icon: 'error',
    allowOutsideClick: false,
  }
  return Swal.fire({
    ...defaultOptionList,
    ...optionList,
  })
}
