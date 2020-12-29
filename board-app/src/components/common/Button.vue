<template>
	<v-btn :color="color" class="ma-2 white--text" rounded small>
		<v-icon small>
			{{ icon }}
		</v-icon>
		<span style="width:5px;"></span>
		{{ title }}
	</v-btn>
</template>

<script>
export default {
	props: ['color', 'icon', 'title'],
}
</script>

<style></style>
