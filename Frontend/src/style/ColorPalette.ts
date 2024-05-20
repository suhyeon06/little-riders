const COLOR_PALETTE: { [key: string]: string } = {
  darkgreen: '#007F73',
  lightgreen: '#4CCD99',
  yellow: '#FFC700',
  lightyellow: 'FFF455',
  lightgray: '#D9D9D9',
  darkgray: '#6E6E6E',
  red: '#F24E1E',
  pink: '#D53F8C',
  orange: '#F7934F',
}
export type ColorPalette = keyof typeof COLOR_PALETTE

export default COLOR_PALETTE
