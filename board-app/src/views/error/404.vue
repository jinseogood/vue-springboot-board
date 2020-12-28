<template>
	<v-container>
		<v-card elevation="10" outlined width="100%" class="mx-auto">
			<v-card-title>
				<v-icon large color="black">
					mdi-cancel
				</v-icon>
				<span style="width:5px;"></span>
				<span class="mr-2">404 Error</span>
			</v-card-title>
			<v-card-text>
				<span class="mr-2">Page Not Found</span>
			</v-card-text>
			<v-card-actions>
				<v-btn @click="prev" color="blue-grey" text>
					<v-icon>
						mdi-arrow-left
					</v-icon>
					<span class="mr-2">Back</span>
				</v-btn>
			</v-card-actions>
		</v-card>
	</v-container>
</template>

<script>
export default {
	name: 'PageNotFound',
	methods: {
		prev() {
			this.prevPage()
		},
	},
}
</script>

<style></style>
