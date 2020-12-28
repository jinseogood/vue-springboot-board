<template>
	<v-app-bar app color="deep-orange darken-1" dark>
		<v-btn @click="movePage('/home')" text>
			<span class="mr-2">Home</span>
		</v-btn>
		<v-btn @click="movePage('/list')" text>
			<span class="mr-2">Board</span>
		</v-btn>
	</v-app-bar>
</template>

<script>
export default {
	name: 'AppHeader',
}
</script>

<style></style>
