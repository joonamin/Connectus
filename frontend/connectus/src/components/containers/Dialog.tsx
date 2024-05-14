import {defaultColors} from '@/constants/colors';
import React from 'react';
import {Modal, ModalProps, SafeAreaView, StyleSheet} from 'react-native';
import MainContainer from '@/components/containers/MainContainer';

/**
 * Modal을 생성합니다
 *
 * @returns Dialog
 */
export default function Dialog({
  children,
  style,
  onRequestClose,
  ...props
}: ModalProps) {
  return (
    <Modal
      transparent={true}
      onRequestClose={onRequestClose}
      animationType="fade"
      {...props}>
      <SafeAreaView style={styles.modalContainer} onTouchEnd={onRequestClose}>
        <MainContainer
          style={[styles.modal, style]}
          onTouchEnd={e => {
            // stopPropagation으로 modal 내 touch 시
            // modal이 닫히는 현상 방지
            e.stopPropagation();
          }}>
          {children}
        </MainContainer>
      </SafeAreaView>
    </Modal>
  );
}

const styles = StyleSheet.create({
  modalContainer: {
    flex: 1,
    flexDirection: 'column',
    width: '100%',
    justifyContent: 'center',
    alignItems: 'stretch',
    height: '100%',
    backgroundColor: 'rgba(0, 0, 0, 0.3)',
  },
  modal: {
    paddingVertical: 30,
    borderRadius: 15,
    margin: 15,
    backgroundColor: defaultColors.surfaceContainerHigh,
  },
});
