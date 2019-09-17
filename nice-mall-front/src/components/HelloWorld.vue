<template>
  <div class="hello">
    <h1>{{ count }}</h1>
    <button @click="handleReload">刷新</button>
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  inject: ['reload'],
  data () {
    return {
      count: 0
    }
  },
  created(){
    this.countADD();
  },
  methods:{
    countADD(){
      setInterval(()=>{
        this.count++;
      },1000)
    },
    handleReload(){
      this.reload();
    }
  }
}
</script>

<style scoped>
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
