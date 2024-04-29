const colors = {
  background: '#25242a',
  buttonBackground: '#38363e',
  primaryColorPink: '#f859fd',
  primaryColorBlue: '#32b5ff',
  secondaryColor: '#9b9b9b',
  dividerColor: '#414141',
  white: '#ffffff',
} as const;

export type ColorTypes = keyof typeof colors;
export type ColorCodes = (typeof colors)[ColorTypes];

export default colors;
