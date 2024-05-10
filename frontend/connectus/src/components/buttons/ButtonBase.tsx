import {StyleSheet, TextProps, View, ViewProps} from 'react-native';
import React from 'react';
import Shape, {ShapeProps} from '@/components/containers/Shape';
import CustomTouchable, {
  CustomTouchableProps,
} from '@/components/buttons/CustomTouchable';
import LabelLargeText from '@/components/text/label/LabelLargeText';

export interface ButtonBaseProps extends CustomTouchableProps {
  icon?: React.ReactNode;
  wrapperStyle?: ShapeProps['style'];
  containerStyle?: ViewProps['style'];
}

export default function ButtonBase({
  icon,
  wrapperStyle,
  containerStyle,
  children,
  ...props
}: ButtonBaseProps) {
  return (
    <Shape rounding="extra-large" corner="full" style={wrapperStyle}>
      <CustomTouchable {...props}>
        {typeof icon !== 'undefined' ? (
          <View style={[styles.button, styles.buttonWithIcon, containerStyle]}>
            {icon}
            {children}
          </View>
        ) : (
          <View style={[styles.button, containerStyle]}>{children}</View>
        )}
      </CustomTouchable>
    </Shape>
  );
}

export function ButtonText({...props}: TextProps) {
  return <LabelLargeText {...props} />;
}

const styles = StyleSheet.create({
  button: {
    height: 40,
    paddingHorizontal: 24,
    flexDirection: 'row',
    justifyContent: 'center',
    alignItems: 'center',
    gap: 8,
  },
  buttonWithIcon: {
    paddingLeft: 16,
  },
});
