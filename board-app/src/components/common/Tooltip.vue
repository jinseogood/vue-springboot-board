<template>
	<div>
		<v-tooltip v-bind="$attrs">
			<template v-slot:activator="{ on, attrs }">
				<v-icon v-on="on" v-bind="attrs" style="padding:0 10px;">
					{{ iconName }}
				</v-icon>
			</template>
			<span> {{ title }}<br />{{ content }} </span>
		</v-tooltip>
	</div>
</template>

<script>
export default {
	props: {
		iconName: String,
		title: String,
		content: String,
	},
}
</script>

<style></style>
