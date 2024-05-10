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
