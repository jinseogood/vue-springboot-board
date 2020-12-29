<template>
	<v-btn
		class="ma-2 white--text"
		:color="color"
		:dark="dark || false"
		:elevation="elevation || undefined"
		:icon="icon || false"
		:large="large || false"
		:outlined="outlined || false"
		:rounded="rounded || false"
		:small="small || false"
		:text="text || false"
		:x-large="xlarge || false"
		:x-small="xsmall || false"
	>
		<v-icon small>
			{{ iconName }}
		</v-icon>
		<span style="width:5px;"></span>
		{{ btnName }}
	</v-btn>
</template>

<script>
export default {
	props: {
		color: String,
		dark: Boolean,
		elevation: Number || String,
		icon: Boolean,
		large: Boolean,
		outlined: Boolean,
		rounded: Boolean,
		small: Boolean,
		text: Boolean,
		xlarge: Boolean,
		xsmall: Boolean,
		iconName: String,
		btnName: String,
	},
}
</script>

<style></style>
