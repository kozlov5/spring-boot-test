<template>
    <div class="add-owner">
        <label for="firstName">First name:</label>
        <input id="firstName" type="text" v-model="firstName">
        <label for="lastName">Last name:</label>
        <input id="lastName" type="text" v-model="lastName">
        <button @click="createOwner">
            Create
        </button>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                firstName: '',
                lastName: '',
            }
        },
        methods: {
            createOwner() {
                this.$http.post('http://localhost:8090/owner/create', {
                    firstName: this.firstName,
                    lastName: this.lastName,
                }).then(result => {
                    console.log(result);
                    this.firstName = '';
                    this.lastName = '';
                    this.$parent.$emit('updateGrid');
                }, error => {
                    console.log('CREATE error::', error);
                });
            }
        }
    }
</script>

<style>
    .add-owner {
        display: flex;
        flex-direction: column;
        margin: 10px;
        width: 395px;
    }
</style>