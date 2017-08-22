<template>
    <div class="add-car">
        <div>Add car to {{ownerName}}</div>
        <label for="name">Name:</label>
        <input id="name" type="text" v-model="name">
        <label for="model">Model:</label>
        <input id="model" type="text" v-model="model">
        <button @click="createCar">Create</button>
    </div>
</template>

<script>
    export default {
        props: ['owner', 'ownerName'],
        data() {
            return {
                model: '',
                name: ''
            }
        },
        methods: {
            createCar() {
                console.log(this);
                this.$http.post('http://localhost:8090/car/create', {
                    model: this.model,
                    name: this.name,
                    ownerId: this.owner
                }).then(result => {
                    console.log(result);
                    this.model = '';
                    this.name = '';
                    this.$parent.$emit('updateGrid');
                }, error => {
                    console.log('CREATE error::', error);
                });
            }
        }
    }
</script>

<style>
    .add-car {
        display: flex;
        flex-direction: column;
        margin: 10px;
        width: 395px;
    }
</style>