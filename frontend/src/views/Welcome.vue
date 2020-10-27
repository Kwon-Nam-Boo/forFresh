<template>
  <v-carousel 
    v-model="model" 
    vertical
    vertical-delimiters
    hide-delimiter-background
    height="100%"
    touchless
    v-touch="{up: () => swipe('Up'),
        down: () => swipe('Down')}"
  >
    <v-carousel-item
      v-for="(color, i) in colors"
      :key="color"
    >
      <v-sheet
        :color="color"
        height="100%"
        tile
      >
        <v-row
          class="fill-height"
          align="center"
          justify="center"
        >
          <div class="display-3">
            Slide {{ i + 1 }}
          </div>
        </v-row>
      </v-sheet>
    </v-carousel-item>
  </v-carousel> 
</template>

<script>

export default {
  created() {
    this.$emit('updateTitle', null);
  },
  data() {
    return {
      model: 0,
      colors: [
        'primary',
        'secondary',
        'yellow darken-2',
        'red',
        'orange',
      ],
    };
  },
  methods: {
    //위아래로 드래그하여 화면전환
    swipe(direction) {
      if(direction.includes("Up")){
        this.model++
      } else if(direction.includes("Down")){
        this.model--
      }
    },
  },
  
};
</script>
