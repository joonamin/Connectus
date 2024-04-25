import colors from '@/constants/colors';
import {DefaultTheme} from '@react-navigation/native';

export const defaultTheme = {
  ...DefaultTheme,
  dark: true,
  colors: {
    ...DefaultTheme.colors,
    background: colors.background,
    text: colors.white,
    border: colors.secondaryColor,
    card: colors.dividerColor,
  },
};
