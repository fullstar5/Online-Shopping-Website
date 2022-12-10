<template >
  <div>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <iframe id ="iframe" class="metaverse" scrolling="no" ref="frame" src="../static/metaverse/index.html" width="65%" height="70%">
    </iframe>

    <div class="introduce" style="background-color:rgba(255,255,255,0.5); width:16%; height:70%; position:absolute; top:20.5%; left:0.7%; ">
      <div style="margin-left:5%; margin-right:3%">
      <br>
      <img class="player" id="Service" style="width:90%; margin-bottom:2%" src="./images/player.png" alt="player" />
      <h3 style="margin-bottom:2%">Toy exhibition visit method</h3>
      <h4 style="margin-bottom:2%">1. Click on START and you will enter the character selection interface.</h4>
      <h4 style="margin-bottom:2%">2. Use the keyboard LEFT (<-) and RIGHT (->) keys to select a character you like.</h4>
      <h4 style="margin-bottom:4%">3. Press the SPACE bar and you can enter the exhibition.</h4>
      <br>
      <h3 style="margin-bottom:2%">Character control method</h3>
      <h4 style="margin-bottom:2%">1. Use the keyboard up, down, left, and right keys to control character movement.</h4>
      <h4 style="margin-bottom:2%">2. Hold down the left mouse button and drag the pointer to move the view.</h4>
      <h4 style="margin-bottom:2%">3. Use the mouse wheel to zoom in or out of view.</h4>
      </div>
    </div>

    <div style="background-color:rgba(255,255,255,0.5); width:16%; height:70%; position:absolute; top:20.5%; right:0.5%;" >
      <div style="margin-left:5%; margin-right:3%">
      <br>
      <h3 style="margin-bottom:5%">Metaverse Toy Exhibition Introduction</h3>
      <h4 style="margin-bottom:5%">MonBear is the curator of this toy exhibit. He held the first toy exhibition in this virtual universe, hoping to befriend every child who came to his virtual universe.</h4>
      <h4 style="margin-bottom:5%">Please enjoy playing here and browse through MonBear's carefully selected toys for you!</h4>
      <h4 style="margin-bottom:90%">Please enjoy playing here and browse through MonBear's carefully selected toys for you!</h4>

      <h4 style="margin-bottom:2%">(Note: Load times may be long)</h4>
      </div>
    </div>

  </div>

</template>


<script>
  export default{
  mounted() {
    document.querySelector('body').setAttribute('style', 'background-color:#B0E0E6')
  },
  beforeDestroy() {
    document.querySelector('body').removeAttribute('style')
  },
  }
</script>

<style scoped>
.metaverse{
  position:absolute;
  left:17.5%;
}
</style>
