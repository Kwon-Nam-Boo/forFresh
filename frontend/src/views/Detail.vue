<template>
  <div>
    <nav-bar :title="title"></nav-bar>
     
  </div>
</template>
<script>
import NavBar from "../components/NavBar";

export default {
    components: {
    NavBar,
    
  },
   data() {
    return {
      title:"상세페이지",
      tab: null,
      items: [
        'ExpirationDate', 'Entrees', 'Deserts', 'Cocktails',
      ],
    }
  },
}
</script>