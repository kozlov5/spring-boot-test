<template>
    <div class="add-owner">
        <div v-if="owner.id > 0">Edit {{owner.name}}</div>
        <label for="firstName">First name:</label>
        <input id="firstName" type="text" v-model="firstName">
        <label for="lastName">Last name:</label>
        <input id="lastName" type="text" v-model="lastName">
        <button @click="createOwner">
            <span v-if="owner.id === 0">Create</span>
            <span v-else>Save</span>
        </button>
    </div>
</template>

<script>
    export default {
        props: ['owner'],
        data() {
            return {
                firstName: this.owner.name ? this.owner.name.split(' ')[0] : 'error',
                lastName: this.owner.name ? this.owner.name.split(' ')[1] : 'error',
            }
        },
        beforeUpdate() {
            this.firstName = this.owner.name ? this.owner.name.split(' ')[0] : 'error';
            this.lastName = this.owner.name ? this.owner.name.split(' ')[1] : 'error';
        },
        methods: {
            createOwner() {
                const method = this.owner.id === 0 ? 'create' : ('edit/' + this.owner.id);
                this.$http.post('http://localhost:8090/owner/' + method, {
                    firstName: this.firstName,
                    lastName: this.lastName,
                    id: this.owner.id
                }).then(result => {
                    console.log(result);
                    this.firstName = '';
                    this.lastName = '';
                    this.owner = 0;
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