import { create } from 'zustand'

interface ModalController {
  addTerminalModal: boolean
  addDriverModal: boolean
  addTeacherModal: boolean
  addShuttleModal: boolean
}
type ModalTypes =
  | 'addTerminalModal'
  | 'addDriverModal'
  | 'addTeacherModal'
  | 'addShuttleModal'

type State = {
  modalController: ModalController
}
type Actions = {
  changeModalState: (type: ModalTypes) => void
}

export const modalStore = create<State & Actions>((set) => ({
  modalController: {
    addTerminalModal: false,
    addDriverModal: false,
    addTeacherModal: false,
    addShuttleModal: false,
  },
  changeModalState: (type: ModalTypes) =>
    set((state) => ({
      modalController: {
        ...state.modalController,
        [type]: !state.modalController[type],
      },
    })),
}))
