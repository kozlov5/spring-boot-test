<template>
    <div class="edit-form">
        <router-link to="/">Main page</router-link>
        <h1>{{name}}</h1>
        <label for="firstName">First name:</label>
        <input id="firstName" type="text" v-model="firstName">
        <label for="lastName">Last name:</label>
        <input id="lastName" @keyup.enter="saveOwner" type="text" v-model="lastName">
        <button @click="saveOwner">Save</button>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                firstName: '',
                lastName: '',
                id: 0
            }
        },
        methods: {
          saveOwner() {
              this.$http.post('owner/edit/' + this.id, {
                  firstName: this.firstName,
                  lastName: this.lastName,
                  id: this.id,
              }).then(result => {
                  this.$router.push('/');
              }, err => {
                  console.log('error::', err);
              })
          }
        },
        created() {
            this.$http.get('owner/get/' + this.$route.params.id).then(result => {
                this.firstName = result.body.firstName;
                this.lastName = result.body.lastName;
                this.name = result.body.firstName + ' ' + result.body.lastName;
                this.id = result.body.id;
            }, error => {
                console.log('GET error::', error)
            });
        }
    }
</script>

<style>
    .edit-form {
        width: 200px;
        display: flex;
        flex-direction: column;
    }

    .edit-form>label, .edit-form>button {
        margin-top: 10px;
    }
</style>