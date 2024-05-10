import React from 'react';
import {StyleSheet, View, ViewProps, ViewStyle} from 'react-native';

export interface ShapeProps extends ViewProps {
  rounding?:
    | 'none'
    | 'extra-small'
    | 'small'
    | 'medium'
    | 'large'
    | 'extra-large';
  corner?: 'full' | 'top' | 'bottom' | 'start' | 'end' | 'none';
}

const radii: Record<Exclude<ShapeProps['rounding'], undefined>, number> = {
  'extra-large': 28,
  large: 16,
  medium: 12,
  small: 8,
  'extra-small': 4,
  none: 0,
};

const styles = StyleSheet.create({
  shape: {
    overflow: 'hidden',
  },
});

export default function Shape({
  rounding,
  corner,
  style,
  children,
  ...props
}: ShapeProps) {
  if (typeof rounding === 'undefined') {
    rounding = 'medium';
  }
  if (typeof corner === 'undefined') {
    corner = 'full';
  }

  let shapeStyle: ViewStyle | undefined;
  switch (corner) {
    case 'full':
      shapeStyle = {
        borderRadius: radii[rounding],
      };
      break;

    case 'top':
      shapeStyle = {
        borderTopStartRadius: radii[rounding],
        borderTopEndRadius: radii[rounding],
      };
      break;

    case 'bottom':
      shapeStyle = {
        borderBottomStartRadius: radii[rounding],
        borderBottomEndRadius: radii[rounding],
      };
      break;

    case 'start':
      shapeStyle = {
        borderStartStartRadius: radii[rounding],
        borderStartEndRadius: radii[rounding],
      };
      break;

    case 'end':
      shapeStyle = {
        borderEndStartRadius: radii[rounding],
        borderEndEndRadius: radii[rounding],
      };
      break;
  }

  const appliedStyles: ViewProps['style'] = [styles.shape];
  if (typeof shapeStyle !== 'undefined') {
    appliedStyles.push(
      StyleSheet.create({
        shape: shapeStyle,
      }).shape,
    );
  }

  return (
    <View style={[...appliedStyles, style]} {...props}>
      {children}
    </View>
  );
}
