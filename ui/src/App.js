import React from "react";
import "./App.css";
import CanvasJSReact from "./assets/canvasjs.react.js";
let CanvasJSChart = CanvasJSReact.CanvasJSChart;

const city = ["Pune", "Chennai", "Kolkata", "Delhi", "Mumbai", "Banglore"];

class App extends React.Component {
  state = {
    selectedCity: "Pune",
    selectedRegion: "",
    toDate: "",
    fromDate: ""
  };
  citySelector = city => {
    return city.map((item, index) => (
      <option name={item} value={item}>
        {item}
      </option>
    ));
  };

  handleSelect = e => {
    this.setState({ selectedCity: e.target.value });
    console.log(e.target.value);
  };

  render() {
    const options = {
      animationEnabled: true,
      title: {
        text: "Weather report"
      },
      axisY: {
        title: "Temperature",
        titleFontColor: "#4F81BC",
        lineColor: "#4F81BC",
        labelFontColor: "#4F81BC",
        tickColor: "#4F81BC"
      },
      axisY2: {
        title: "Rainfall",
        titleFontColor: "#C0504E",
        lineColor: "#C0504E",
        labelFontColor: "#C0504E",
        tickColor: "#C0504E"
      },
      toolTip: {
        shared: true
      },
      legend: {
        cursor: "pointer"
      },
      data: [
        {
          type: "column",
          name: "Temperature",
          legendText: "Temperature",
          showInLegend: true,
          dataPoints: [
            { label: "Day 1", y: 26 },
            { label: "Day 2", y: 30 },
            { label: "Day 3", y: 27 },
            { label: "Day 4", y: 32 },
            { label: "Day 5", y: 31 },
            { label: "Day 6", y: 25 },
            { label: "Day 7", y: 23 }
          ]
        },
        {
          type: "column",
          name: "Rainfall(mm)",
          legendText: "Rainfall",
          axisYType: "secondary",
          showInLegend: true,
          dataPoints: [
            { label: "Day 1", y: 50 },
            { label: "Day 2", y: 60 },
            { label: "Day 3", y: 100 },
            { label: "Day 4", y: 40 },
            { label: "Day 5", y: 45 },
            { label: "Day 6", y: 51 },
            { label: "Day 7", y: 49 }
          ]
        }
      ]
    };

    return (
      <div>
        <div>
          <form>
            <label for="city">City: </label>
            <select
              name="city"
              onChange={this.handleSelect}
              value={this.state.selectedCity}
            >
              {this.citySelector(city)}
            </select>
            <label for="region">Region: </label>
            <select
              name="region"
              onChange={this.handleRegion}
              value={this.state.selectedRegion}
            >
              {this.citySelector(city)}
            </select>
            <label for="fromDate">from: </label>
            <input type="date" name="fromDate" value={this.state.fromDate} />
            <label for="toDate">to: </label>
            <input type="date" name="toDate" value={this.state.toDate} />
          </form>
        </div>
        <CanvasJSChart options={options} />
      </div>
    );
  }
}

export default App;
