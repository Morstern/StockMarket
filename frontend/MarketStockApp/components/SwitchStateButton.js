import React from "react";
import { View, TouchableOpacity, Text } from "react-native";

import {
  backgroundFocusColor,
  backgroundUnFocusColor
} from "../constants/colorConstants";
export const SwitchStateButton = props => {
  return (
    <View
      style={[
        props.styles.viewStyle,
        props.currentMode === props.mode
          ? { backgroundColor: backgroundFocusColor }
          : { backgroundColor: backgroundUnFocusColor }
      ]}
    >
      <TouchableOpacity
        style={props.styles.buttonStyle}
        onPress={() => {
          props.setCurrentMode(props.mode);
        }}
      >
        <Text style={props.styles.textStyle}>{props.text}</Text>
      </TouchableOpacity>
    </View>
  );
};
