import { Doughnut } from 'vue-chartjs'

export default {
  extends: Doughnut,
  mounted () {
    this.renderChart({
      
      datasets: [
        {
          backgroundColor: [
            '#41D3BD',
            
          ],
          data: [40, 60]
        }
      ]
    }, {responsive: true, maintainAspectRatio: false})
    
  }
}