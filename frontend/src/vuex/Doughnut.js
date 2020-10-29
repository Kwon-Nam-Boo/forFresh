import { Doughnut } from 'vue-chartjs'

export default {
  extends: Doughnut,
  mounted () {
    this.renderChart({
      
      datasets: [
        {
          backgroundColor: [
            '#9DC8C8','#e2efef'
            
          ],
          data: [40, 60]
        }
      ]
    }, {responsive: true, maintainAspectRatio: false})
    
  }
}