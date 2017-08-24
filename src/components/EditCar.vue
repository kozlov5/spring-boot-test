<template>
    <div class="edit-car">
        <router-link to="/">Main page</router-link>
        <h1>{{fullName}}</h1>
        <label for="name">Name:</label>
        <input id="name" type="text" v-model="name">
        <label for="model">Model:</label>
        <input id="model" type="text" v-model="model">
        <button @click="saveCar">Save</button>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                model: '',
                name: '',
                id: 0,
                ownerId: 0
            }
        },
        created() {
          this.$http.get('car/get/' + this.$route.params.id).then(result => {
              this.model = result.body.model;
              this.name = result.body.name;
              this.id = result.body.id;
              this.ownerId = result.body.ownerId;
              this.fullName = result.body.name + ' ' + result.body.model;
          }, err => {
              console.log('error::', err);
          })
        },
        methods: {
            saveCar() {
                this.$http.put('car/edit/' + this.id, {
                    model: this.model,
                    name: this.name,
                    id: this.id,
                    owner: {id: this.ownerId}
                }).then(result => {
                    this.$router.push('/');
                }, err => {
                    console.log('error::', err);
                })
            }
        }
    }
</script>

<style>
    .edit-car {
        width: 200px;
        display: flex;
        flex-direction: column;
    }

    .edit-car>label, .edit-car>button {
        margin-top: 10px;
    }
</style>