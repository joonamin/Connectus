import colors from '@/constants/colors';
import React from 'react';
import {Platform, StyleSheet, View} from 'react-native';
import MapView, {
  LatLng,
  MapViewProps,
  PROVIDER_GOOGLE,
  Polyline,
} from 'react-native-maps';
import StartMarker from '@/components/map/StartMarker';
import FinishMarker from '@/components/map/FinishMarker';
import {ViewProps} from 'react-native';

/**
 * RouteMap을 생성할 시 전달할 인자를 지정합니다
 */
export interface RouteMapProps extends ViewProps {
  /**
   * 산책 경로
   */
  routes: LatLng[];
}

/**
 * 경로를 지도에 표시합니다
 *
 * @returns RouteMap
 */
export default class RouteMap extends React.Component {
  /**
   * 지도 객체
   */
  private map: MapView;

  /**
   * 지도에 표시할 경로 목록
   */
  protected routes: LatLng[];

  /**
   * View에 적용할 props
   */
  protected viewProps: ViewProps;

  /**
   * RouteMap을 생성합니다
   *
   * @param props RouterMap 생성 시 사용할 인자
   */
  constructor(props: RouteMapProps) {
    super(props);

    this.viewProps = props;
    this.routes = props.routes;

    const mapProps: MapViewProps = {
      style: styles.map,
      scrollEnabled: false,
      zoomEnabled: false,
      provider: PROVIDER_GOOGLE,
      onMapReady: this.onMapReady.bind(this),
      children: (
        <>
          <StartMarker coordinate={this.routes[0]} />
          <FinishMarker coordinate={this.routes[this.routes.length - 1]} />
          <Polyline
            coordinates={this.routes}
            strokeWidth={8}
            strokeColor={colors.dividerColor}
          />
        </>
      ),
    };

    this.map = new MapView(mapProps);
  }

  /**
   * 지도 준비 완료 시 실행할 함수입니다
   */
  protected onMapReady() {
    // 지도를 적절한 위치에 위치
    if (Platform.OS === 'ios') {
      this.map.fitToElements();
    } else {
      this.map.fitToCoordinates(this.routes, {
        animated: false,
        edgePadding: {
          top: 50,
          bottom: 30,
          left: 30,
          right: 30,
        },
      });
    }
  }

  /**
   * RouteMap을 render합니다
   *
   * @returns RouteMap
   */
  render() {
    const {style: viewStyle, ...containerProps} = this.viewProps;
    console.debug(this.map.props);

    return (
      <View style={[styles.mapContainer, viewStyle]} {...containerProps}>
        {this.map.render()}
      </View>
    );
  }
}

const styles = StyleSheet.create({
  mapContainer: {
    aspectRatio: '1 / 1',
    borderRadius: 15,
    overflow: 'hidden',
    alignItems: 'stretch',
  },
  map: {
    flexBasis: '100%',
  },
});
