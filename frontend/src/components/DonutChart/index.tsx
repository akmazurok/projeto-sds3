import axios from 'axios';
import { useEffect, useState } from 'react';
import Chart from 'react-apexcharts';
import { SaleSum } from 'types/sale';
import { BASE_URL } from 'utils/requests';

type ChartData = {
    labels: string[];
    series: number[]
}

const DonutChart = () => {
    
    //
    const [chartData, setChartData] = useState<ChartData>({ labels: [], series: []});

    useEffect(() => {
        axios.get(`${BASE_URL}/sales/amount-by-seller`)
            .then(response => {
                const data = response.data as SaleSum[];
                const myLabels = data.map(x => x.sellerName);
                const mySeries = data.map(x => x.sum);

                setChartData({ labels: myLabels, series: mySeries });
            });

    }, []);

    
    // 2 - FORMA ERRADA
    //let chartData : ChartData = { labels: [], series: []};

    
    /* 3- FORMA ERRADA - para testar se esta chamando os dados da API
    //usa crase pra montar essa url
    
    axios.get(`${BASE_URL}/sales/amount-by-seller`)
        .then( response => {
            console.log(response.data);

        }); */
    
    /* 4 - FORMA ERRADA
    //dessa forma os dados ficam na variavel mas nao chama o grafico pq essa chamada eh assincrona
    axios.get(`${BASE_URL}/sales/amount-by-seller`)
        .then( response => {
            const data = response.data as SaleSum[];
            const myLabels = data.map(x => x.sellerName);
            const mySeries = data.map(x => x.sum);

            //chartData = { labels: myLabels, series: mySeries};
            setChartData({labels: myLabels, series: mySeries}});

            console.log(chartData);

        }); */
    
    
    /* 1 - DADOS ESTATICOS - usados na construcao do frontend
    const mockData = { 
        series: [477138, 499928, 444867, 220426, 473088],
        labels: ['Anakin', 'Barry Allen', 'Kal-El', 'Logan', 'Padmé']
    } */
    
    const options = {
        legend: {
            show: true
        }
    }
    
    return (
       <Chart
            options={{...options, labels: chartData.labels}} 
            series={chartData.series}
            type="donut"
            height="240"
       />
    );
  }
  
  export default DonutChart;