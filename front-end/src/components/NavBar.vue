<script>
export default {
    name: 'NavBar',

}
</script>

<template>
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary mb-3 px-4">
        <div class="container-fluid">
            <RouterLink class="navbar-brand" :to="{ name: 'home' }">
                <i class="fa-solid fa-camera-retro" style="font-size: 2.5rem"></i>
            </RouterLink>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbar-content" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbar-content">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <RouterLink class="nav-link not-active" :to="{ name: 'home' }">Photos</RouterLink>
                    </li>
                    <li class="nav-item">
                        <RouterLink class="nav-link not-active" :to="{ name: 'second' }">Contacts</RouterLink>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</template>

<style></style>