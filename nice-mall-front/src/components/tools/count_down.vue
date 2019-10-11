<template>
    <span :endTime="endTime" :startTime="startTime" :callback="callback" :endText="endText">
        <slot>
            {{content}}
        </slot>
    </span>
</template>
<script>
  export default {
    data(){
      return {
        content: '',
      }
    },
    props:{
      endTime:{
        type: String,
        default :''
      },
      startTime:{
        type: String,
        default :''
      },
      endText:{
        type : String,
        default:'已结束'
      },
      callback : {
        type : Function,
        default :''
      }
    },
    mounted () {
      this.countdowm(this.startTime,this.endTime)
    },
    methods: {
      countdowm(start,end){
        let self = this;
        // let now = new Data().getTime();
        let time = end - start;
        if(time > 0) {
          let hour = Math.floor((time / 3600000) % 24);
          let min = Math.floor((time / 60000) % 60);
          let sec = Math.floor((time / 1000) % 60);
          hour = hour < 10 ? "0" + hour : hour;
          min = min < 10 ? "0" + min : min;
          sec = sec < 10 ? "0" + sec : sec;
          let format = '';
          let timer = setInterval(() => {
            sec--;
            sec = sec < 10 ? "0" + sec : sec;
            if(sec <= 0 && min >0){
              min--;
              min = min < 10 ? "0" + min : min;
              sec = 59;
              if(min <0){
                min = '00';
                sec = '00' ;
              }
            }
            if(sec > 0 && min < 0){
              sec--;
              sec = sec < 10 ? "0" + sec : sec;
            }
            if(hour <=0 ){
              hour = '00'
            }
            format = `${hour}:${min}:${sec}`;
            self.content = format;
            if (sec <= 0) {
              clearInterval(timer);
              self.content = '活动已结束';

            }
          }, 1000);
        }else{
          self._callback();
        }
      },
      _callback(){
        if(this.callback && this.callback instanceof Function){
          this.callback(...this);
        }
      }
    }
  }
</script>
