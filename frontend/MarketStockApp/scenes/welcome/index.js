import React, { useState } from "react";
import {
  ImageBackground,
  Text,
  View,
  StyleSheet,
  TextInput,
  Button
} from "react-native";
import { Icon } from "react-native-elements";
import { ButtonText, HeaderText } from "../../constants/welcomeConstants";

export default Welcome = () => {
  const [currentMode, setCurrentMode] = useState("login");
  const [credentials, setCredentials] = useState({ email: "", password: "" });

  const handleChange = event => {
    const { name, text } = event;
    setCredentials({ [name]: text });
  };

  return (
    <ImageBackground
      style={styles.root}
      source={require("../../assets/images/helloquence-OQMZwNd3ThU-unsplash.jpg")}
    >
      <View style={styles.headerContainer}>
        <Text style={styles.header}>Welcome to StockMarket</Text>
        <Icon name="trending-up" type="material" size={36} />
      </View>
      <View style={styles.inputContainer}>
        <Text>{HeaderText[currentMode]}</Text>
        <TextInput
          placeholder="E-mail"
          value={credentials.email}
          onChange={handleChange}
        />
        <TextInput
          placeholder="Password"
          value={credentials.password}
          onChange={handleChange}
        />
      </View>
    </ImageBackground>
  );
};

const styles = StyleSheet.create({
  root: {
    width: "100%",
    height: "100%"
  },
  headerContainer: {
    flexDirection: "row",
    paddingTop: 50,
    paddingBottom: 50,
    alignContent: "center",
    justifyContent: "center"
  },
  header: {
    fontSize: 26,
    color: "rgba(255,255,255,1)",
    textAlign: "center"
  },

  inputContainer: {
    flexDirection: "column",
    paddingTop: "50%"
  }
});
