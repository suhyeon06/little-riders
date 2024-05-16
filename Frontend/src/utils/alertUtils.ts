import COLOR_PALETTE from '@style/ColorPalette'
import Swal, { SweetAlertOptions } from 'sweetalert2'

export const showQuestionAlert = (optionList: SweetAlertOptions) => {
  const defaultOptionList: SweetAlertOptions = {
    icon: 'question',
    showDenyButton: true,
    confirmButtonText: '예',
    confirmButtonColor: COLOR_PALETTE.lightgreen,
    denyButtonText: '아니오',
    allowOutsideClick: false,
  }

  return Swal.fire({ ...defaultOptionList, ...optionList })
}

export const showSuccessAlert = (optionList: SweetAlertOptions) => {
  const defaultOptionList: SweetAlertOptions = {
    icon: 'success',
    allowOutsideClick: false,
  }

  return Swal.fire({
    ...defaultOptionList,
    ...optionList,
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

export const showInitShuttleAlert = (shuttleName: string) => {
  const defaultOptionList: SweetAlertOptions = {
    text: `${shuttleName}가 출발했습니다.`,
    backdrop: false,
    position: 'bottom',
    timer: 2000,
    timerProgressBar: true,
  }

  return Swal.fire({
    ...defaultOptionList,
  })
}

export const showEndShuttleAlert = (shuttleName: string) => {
  const defaultOptionList: SweetAlertOptions = {
    text: `${shuttleName}가 운행을 종료했습니다.`,
    backdrop: false,
    position: 'bottom',
    timer: 2000,
    timerProgressBar: true,
  }

  return Swal.fire({
    ...defaultOptionList,
  })
}
