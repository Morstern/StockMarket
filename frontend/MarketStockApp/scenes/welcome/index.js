import React, { useState } from "react";
import {
  ImageBackground,
  Text,
  View,
  StyleSheet,
  TextInput,
  TouchableOpacity
} from "react-native";
import { Icon } from "react-native-elements";

import {
  ButtonText,
  HeaderText,
  Mode
} from "../../constants/welcomeScreenMessageConstants";
import {
  textColor,
  backgroundLoginRegisterColor
} from "../../constants/colorConstants";
import { SwitchStateButton } from "../../components/SwitchStateButton";

import axios from "axios";

export default Welcome = () => {
  const [currentMode, setCurrentMode] = useState("login");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  return (
    <ImageBackground
      style={styles.root}
      source={require("../../assets/images/helloquence-OQMZwNd3ThU-unsplash.jpg")}
    >
      <View style={styles.headerContainer}>
        <Text style={styles.header}>Welcome to StockMarket</Text>
        <Icon
          name="trending-up"
          type="material"
          color={"rgba(48, 36, 150 , 0.85)"}
          size={36}
        />
      </View>
      <View style={styles.optionsContainer}>
        <SwitchStateButton
          styles={{
            viewStyle: styles.optionsContainer__ButtonsDiv,
            buttonStyle: styles.optionsContainer__ButtonsDiv_Button,
            textStyle: styles.text
          }}
          currentMode={currentMode}
          mode={Mode.login}
          text="Login"
          setCurrentMode={setCurrentMode}
        />

        <SwitchStateButton
          styles={{
            viewStyle: styles.optionsContainer__ButtonsDiv,
            buttonStyle: styles.optionsContainer__ButtonsDiv_Button,
            textStyle: styles.text
          }}
          currentMode={currentMode}
          mode={Mode.register}
          text="Register"
          setCurrentMode={setCurrentMode}
        />
      </View>
      <View style={styles.inputContainer}>
        <View style={styles.infoContainer}>
          <Text style={[styles.text, { fontSize: 18 }]}>
            {HeaderText[currentMode]}
          </Text>
        </View>
        <TextInput
          style={styles.textInput}
          placeholder="E-mail"
          placeholderTextColor="black"
          autoCompleteType="email"
          keyboardType="email-address"
          value={email}
          onChangeText={setEmail}
        />
        <TextInput
          style={styles.textInput}
          placeholder="Password"
          placeholderTextColor="black"
          secureTextEntry={true}
          value={password}
          onChangeText={setPassword}
        />
        <View style={styles.actionButtonContainer}>
          <TouchableOpacity
            style={styles.button}
            onPress={() => {
              axios
                .get("http://192.168.18.11:8080/stockprice/summary")
                .then(resp => console.log(resp.data))
                .catch(err => console.log(err));
            }}
          >
            <Text style={styles.button__text}>{ButtonText[currentMode]}</Text>
          </TouchableOpacity>
        </View>
      </View>
    </ImageBackground>
  );
};

const styles = StyleSheet.create({
  root: {
    width: "100%",
    height: "100%"
  },
  header: {
    fontSize: 26,
    color: "rgba(255,255,255,1)",
    textAlign: "center"
  },
  headerContainer: {
    flexDirection: "row",
    paddingTop: 50,
    paddingBottom: 50,
    justifyContent: "center"
  },
  optionsContainer: {
    flexDirection: "row",
    justifyContent: "center"
  },
  optionsContainer__ButtonsDiv: {
    flex: 1,
    marginHorizontal: 10,
    backgroundColor: backgroundLoginRegisterColor,
    borderTopLeftRadius: 5,
    borderTopRightRadius: 5
  },
  optionsContainer__ButtonsDiv_Button: {
    padding: 20,
    width: "100%",
    alignItems: "center"
  },
  inputContainer: {
    flexDirection: "column",
    marginHorizontal: 10,
    flex: 1,
    marginBottom: 40,
    backgroundColor: backgroundLoginRegisterColor,
    borderBottomLeftRadius: 5,
    borderBottomRightRadius: 5
  },
  actionButtonContainer: {
    margin: 50
  },
  button: {
    backgroundColor: "white",
    borderRadius: 3,
    padding: 10
  },
  button__text: {
    textAlign: "center"
  },
  text: {
    color: textColor
  },
  textInput: {
    backgroundColor: "white",
    padding: 10,
    margin: 10
  },
  infoContainer: {
    padding: 15
  }
});
