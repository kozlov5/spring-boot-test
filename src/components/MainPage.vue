<template>
    <div id="app">
        <h1>List of owners <button @click="addOwner">+</button></h1>
        <div class="search">
            <input type="text" placeholder="Search" v-model="search">
            <button @click="searchOwners">Search</button>
            <button v-if="searchMode" @click="getOwners">All</button>
        </div>
        <div class="owners-list">
            <div v-for="owner in owners" class="owner">
                <p>{{owner.name}}</p>
                <button class="delete-button" @click="deleteOwner(owner.id)">&#10006;</button>
                <router-link class="edit-button" :to="{path: '/edit/' + owner.id}">&#9999;</router-link>
                <button class="add-car-button" @click="addCar(owner.id, owner.name)">+</button>
                <template v-if="owner.cars.length > 0">
                    <div class="cars-list">
                        <div class="cars-label">Cars:</div>
                        <div v-for="car in owner.cars" class="car">
                            <router-link class="edit-car-button" :to="{path: '/edit/' + car.id + '/car'}">&#9999;</router-link>
                            <button class="delete-car-button" @click="deleteCar(car.id)">X</button>
                            {{car.name + " " + car.model}}

                        </div>
                    </div>
                </template>
                <div v-else class="cars-label">No cars yet</div>
            </div>
        </div>
        <template v-if="!addCarMode">
            <add-owner :owner="selectedOwner"></add-owner>
        </template>
        <template v-else>
            <add-car :owner="owner" :car="car" :owner-name="ownerName"></add-car>
        </template>
    </div>
</template>

<script>
    import AddOwner from "./AddOwner.vue";
    import AddCar from "./AddCar.vue";
    export default {
        components: {AddOwner, AddCar},
        name: 'mainPage',
        data() {
            return {
                header: 'This is list of owners',
                owners: [],
                addCarMode: false,
                owner: 0,
                selectedOwner: {
                    firstName: '',
                    lastName: '',
                    id: 0
                },
                ownerName: '',
                search: '',
                searchMode: false,
                car: 0,
                editMode: false

            }
        },
        methods: {
            addOwner() {
                this.selectedOwner = {
                    firstName: '',
                    lastName: '',
                    id: 0
                };
                this.setCarMode(false);
            },
            editOwner(owner) {
              this.selectedOwner = owner;
            },
            searchOwners() {
                this.$http.get('http://localhost:8090/search', {params: {keyword: this.search, sort: 'down'}}).then(result => {
                    this.owners = result.body;
                    this.searchMode = true;
                }, error => {
                    console.log('GET error::', error)
                })
            },
            setCarMode(status) {
              this.addCarMode = status;
            },
            getOwners() {
                this.$http.get('http://localhost:8090/owners').then(result => {
                    this.owners = result.body;
                    this.search = '';
                    this.searchMode = false;
                }, error => {
                    console.log('GET error::', error)
                })
            },

            addCar(id, name) {
              this.owner = id;
              this.ownerName = name;
              this.setCarMode(true);
            },

            deleteOwner(id) {
                this.$http.delete('http://localhost:8090/owner/delete/' + id).then(result => {
                    let index = -1;
                    this.owners.forEach((item, i) => {
                        if (item.id === id) {
                            index = i;
                        }
                    });
                    if (index !== -1) {
                        this.owners.splice(index, 1);
                    }
                }, error => {
                    console.log('DELETE error::', error)
                })
            },
            deleteCar(id) {
                this.$http.delete('http://localhost:8090/car/delete/' + id).then(result => {
                    this.getOwners();
                }, error => {
                    console.log('DELETE error::', error)
                })
            }
        },
        created() {
            this.getOwners();
        },
        mounted() {
            this.$on('updateGrid', this.getOwners);
        }
    }
</script>

<style>
    .owners-list {
        border: 1px solid;
        width: 400px;
        padding: 5px;
    }

    .owner {
        position: relative;
        margin-top: 5px;
        border: 1px solid;
        padding: 5px;
    }

    .owner > p {
        font-size: 25px;
        margin: 0;
    }

    .owner:first-child {
        margin-top: 0;
    }

    .cars-label {
        font-size: 20px;
        font-weight: bold;
    }

    .delete-button, .edit-button {
        position: absolute;
        right: 5px;
        top: 5px;
        font-weight: bold;
        border: none;
        cursor: pointer;
        background: none;
        font-size: 21px;
    }

    .edit-button {
        right: 33px;
        text-decoration: none;
        top: 4px;
        color: black;
    }

    .add-car-button {
        position: absolute;
        right: 5px;
        bottom: 5px;
        font-weight: bold;
        border: none;
        cursor: pointer;
        background: none;
        font-size: 21px;
    }

    .delete-car-button {
        border: 1px solid;
        background: none;
        margin: 1px;
        font-weight: 900;
        cursor: pointer;
    }

    .search {
        display: flex;
        margin: 10px 0;
        width: 413px;
    }

    .search>input {
        width: 100%;
    }

    .edit-car-button {
        text-decoration: none;
        color: black;
        border: 1px solid;
        padding: 2px 3px;
    }
</style>