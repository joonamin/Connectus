import {ColorValue} from 'react-native';

/**
 * Material Design 3에서 지정된 color role입니다
 *
 * 참조: https://m3.material.io/styles/color/roles
 */
export interface ColorTheme {
  // Primary
  //
  // Use primary roles for the most prominent components across the UI,
  // such as the FAB, high-emphasis buttons, and active states.

  /**
   * High-emphasis fills, texts, and icons against surface
   */
  primary: ColorValue;
  /**
   * Text and icons against primary
   */
  onPrimary: ColorValue;
  /**
   * Standout fill color against surface, for key components like FAB
   */
  primaryContainer: ColorValue;
  /**
   * Text and icons against primary container
   */
  onPrimaryContainer: ColorValue;

  // Secondary
  //
  // Use secondary roles for less prominent components in the UI such
  // as filter chips.

  /**
   * Less prominent fills, text, and icons against surface
   */
  secondary: ColorValue;
  /**
   * Text and icons against secondary
   */
  onSecondary: ColorValue;
  /**
   * Less prominent fill color against surface, for recessive
   * components like tonal buttons
   */
  secondaryContainer: ColorValue;
  /**
   * Text and icons against secondary container
   */
  onSecondaryContainer: ColorValue;

  // Tertiary
  //
  // Use tertiary roles for contrasting accents that balance primary
  // and secondary colors or bring heightened attention to an element
  // such as an input field.

  /**
   * Complementary fills, text, and icons against surface
   */
  tertiary: ColorValue;
  /**
   * Text and icons against tertiary
   */
  onTertiary: ColorValue;
  /**
   * Complementary container color against surface, for components like
   * input fields
   */
  tertiaryContainer: ColorValue;
  /**
   * Text and icons against tertiary container
   */
  onTertiaryContainer: ColorValue;

  // Error
  //
  // Use error roles to communicate error states, such as an incorrect
  // password entered into a text field.

  /**
   * Attention-grabbing color against surface for fills, icons, and
   * text, indicating urgency
   */
  error: ColorValue;
  /**
   * Text and icons against error
   */
  onError: ColorValue;
  /**
   * Attention-grabbing fill color against surface
   */
  errorContainer: ColorValue;
  /**
   * Text and icons against error container
   */
  onErrorContainer: ColorValue;

  // Surface
  //
  // Use surface roles for more neutral backgrounds, and container
  // colors for components like cards, sheets, and dialogs.

  /**
   * Default color for backgrounds
   */
  surface: ColorValue;
  /**
   * Text and icons against any surface color
   */
  onSurface: ColorValue;
  /**
   * Lower-emphasis color for text and icons against any surface color
   */
  onSurfaceVariant: ColorValue;

  /**
   * Lowest-emphasis container color
   */
  surfaceContainerLowest: ColorValue;
  /**
   * Low-emphasis container color
   */
  surfaceContainerLow: ColorValue;
  /**
   * Default container color
   */
  surfaceContainer: ColorValue;
  /**
   * High-emphasis container color
   */
  surfaceContainerHigh: ColorValue;
  /**
   * Highest-emphasis container color
   */
  surfaceContainerHighest: ColorValue;

  // Inverse colors
  //
  // Inverse roles are applied selectively to components to achieve
  // colors that are the reverse of those in the surrounding UI,
  // creating a contrasting effect.

  /**
   * Background fills for elements which contrast against surface
   */
  inverseSurface: ColorValue;
  /**
   * Text and icons against inverse surface
   */
  inverseOnSurface: ColorValue;
  /**
   * Actionable elements, such as text buttons, against inverse surface
   */
  inversePrimary: ColorValue;

  // Surface tint overlays

  /**
   * @deprecated Material Design no longer supports tint overlays
   */
  surfaceTint: ColorValue;

  // Outline

  /**
   * Important boundaries, such as a text field outline
   */
  outline: ColorValue;
  /**
   * Decorative elements, such as dividers
   */
  outlineVariant: ColorValue;

  // Fixed accent colors

  /**
   * Fill color used against surface
   */
  primaryFixed: ColorValue;
  /**
   * Fill color used against surface
   */
  secondaryFixed: ColorValue;
  /**
   * Fill color used against surface
   */
  tertiaryFixed: ColorValue;

  /**
   * A stronger, more emphasized tone relative to the equivalent fixed
   * color
   */
  primaryFixedDim: ColorValue;
  /**
   * A stronger, more emphasized tone relative to the equivalent fixed
   * color
   */
  secondaryFixedDim: ColorValue;
  /**
   * A stronger, more emphasized tone relative to the equivalent fixed
   * color
   */
  tertiaryFixedDim: ColorValue;

  // On fixed accent colors

  /**
   * Used for text and icons which sit on top of the corresponding
   * Fixed color
   */
  onPrimaryFixed: ColorValue;
  /**
   * Used for text and icons which sit on top of the corresponding
   * Fixed color
   */
  onSecondaryFixed: ColorValue;
  /**
   * Used for text and icons which sit on top of the corresponding
   * Fixed color
   */
  onTertiaryFixed: ColorValue;

  /**
   * Used for text and icons needing lower emphasis against the
   * corresponding fixed color
   */
  onPrimaryFixedVariant: ColorValue;
  /**
   * Used for text and icons needing lower emphasis against the
   * corresponding fixed color
   */
  onSecondaryFixedVariant: ColorValue;
  /**
   * Used for text and icons needing lower emphasis against the
   * corresponding fixed color
   */
  onTertiaryFixedVariant: ColorValue;

  // Bright and dim surface roles

  /**
   * Dimmest surface color in light and dark themes
   */
  surfaceDim: ColorValue;
  /**
   * Brightest surface color in light and dark themes
   */
  surfaceBright: ColorValue;

  surfaceVariant: ColorValue;
  background: ColorValue;
  onBackground: ColorValue;

  shadow: ColorValue;
  scrim: ColorValue;
}

/**
 * {@link ColorTheme}을 기반으로 프로젝트의 색상을 지정합니다
 */
export const colorThemes: Record<string, ColorTheme> = {
  light: {
    primary: '#32628d',
    surfaceTint: '#32628d',
    onPrimary: '#ffffff',
    primaryContainer: '#cfe5ff',
    onPrimaryContainer: '#001d34',
    secondary: '#526070',
    onSecondary: '#ffffff',
    secondaryContainer: '#d6e4f7',
    onSecondaryContainer: '#0f1d2a',
    tertiary: '#695779',
    onTertiary: '#ffffff',
    tertiaryContainer: '#f0dbff',
    onTertiaryContainer: '#241532',
    error: '#ba1a1a',
    onError: '#ffffff',
    errorContainer: '#ffdad6',
    onErrorContainer: '#410002',
    background: '#f7f9ff',
    onBackground: '#191c20',
    surface: '#f7f9ff',
    onSurface: '#191c20',
    surfaceVariant: '#dee3eb',
    onSurfaceVariant: '#42474e',
    outline: '#73777f',
    outlineVariant: '#c2c7cf',
    shadow: '#000000',
    scrim: '#000000',
    inverseSurface: '#2d3135',
    inverseOnSurface: '#eff1f6',
    inversePrimary: '#9dcbfc',
    primaryFixed: '#cfe5ff',
    onPrimaryFixed: '#001d34',
    primaryFixedDim: '#9dcbfc',
    onPrimaryFixedVariant: '#134a74',
    secondaryFixed: '#d6e4f7',
    onSecondaryFixed: '#0f1d2a',
    secondaryFixedDim: '#bac8da',
    onSecondaryFixedVariant: '#3a4857',
    tertiaryFixed: '#f0dbff',
    onTertiaryFixed: '#241532',
    tertiaryFixedDim: '#d4bee5',
    onTertiaryFixedVariant: '#514060',
    surfaceDim: '#d8dae0',
    surfaceBright: '#f7f9ff',
    surfaceContainerLowest: '#ffffff',
    surfaceContainerLow: '#f2f3f9',
    surfaceContainer: '#eceef4',
    surfaceContainerHigh: '#e6e8ee',
    surfaceContainerHighest: '#e0e2e8',
  },
  dark: {
    primary: '#9dcbfc',
    surfaceTint: '#9dcbfc',
    onPrimary: '#003355',
    primaryContainer: '#134a74',
    onPrimaryContainer: '#cfe5ff',
    secondary: '#bac8da',
    onSecondary: '#243240',
    secondaryContainer: '#3a4857',
    onSecondaryContainer: '#d6e4f7',
    tertiary: '#d4bee5',
    onTertiary: '#392a49',
    tertiaryContainer: '#514060',
    onTertiaryContainer: '#f0dbff',
    error: '#ffb4ab',
    onError: '#690005',
    errorContainer: '#93000a',
    onErrorContainer: '#ffdad6',
    background: '#101418',
    onBackground: '#e0e2e8',
    surface: '#101418',
    onSurface: '#e0e2e8',
    surfaceVariant: '#42474e',
    onSurfaceVariant: '#c2c7cf',
    outline: '#8c9199',
    outlineVariant: '#42474e',
    shadow: '#000000',
    scrim: '#000000',
    inverseSurface: '#e0e2e8',
    inverseOnSurface: '#2d3135',
    inversePrimary: '#32628d',
    primaryFixed: '#cfe5ff',
    onPrimaryFixed: '#001d34',
    primaryFixedDim: '#9dcbfc',
    onPrimaryFixedVariant: '#134a74',
    secondaryFixed: '#d6e4f7',
    onSecondaryFixed: '#0f1d2a',
    secondaryFixedDim: '#bac8da',
    onSecondaryFixedVariant: '#3a4857',
    tertiaryFixed: '#f0dbff',
    onTertiaryFixed: '#241532',
    tertiaryFixedDim: '#d4bee5',
    onTertiaryFixedVariant: '#514060',
    surfaceDim: '#101418',
    surfaceBright: '#36393e',
    surfaceContainerLowest: '#0b0e12',
    surfaceContainerLow: '#191c20',
    surfaceContainer: '#1d2024',
    surfaceContainerHigh: '#272a2f',
    surfaceContainerHighest: '#32353a',
  },
} as const;

const colors = {
  background: '#25242a',
  buttonBackground: '#38363e',
  primaryColorPink: '#f859fd',
  primaryColorBlue: '#32b5ff',
  secondaryColor: '#9b9b9b',
  dividerColor: '#414141',
  white: '#ffffff',
} as const;

export default colors;
