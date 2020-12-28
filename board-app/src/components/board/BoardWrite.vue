<template>
	<v-container fluid>
		<v-card elevation="10" outlined width="100%" class="mx-auto">
			<v-card-title>
				<span class="mr-2">Write</span>
			</v-card-title>
			<v-card-text>
				<v-text-field
					label="Title"
					:rules="rules"
					:counter="100"
					v-model="title"
				></v-text-field>
				<Editor ref="editor" />
			</v-card-text>
			<v-card-actions>
				<v-spacer></v-spacer>
				<v-btn
					@click="save"
					color="success"
					class="ma-2 white--text"
					rounded
					small
				>
					<v-icon small>
						mdi-content-save-all
					</v-icon>
					<span style="width:5px;"></span>
					Save
				</v-btn>
				<v-btn
					@click="prevPage()"
					color="grey darken-1"
					class="ma-2 white--text"
					rounded
					small
				>
					<v-icon small>
						mdi-arrow-left
					</v-icon>
					<span style="width:5px;"></span>
					Back
				</v-btn>
			</v-card-actions>
		</v-card>
	</v-container>
</template>

<script>
import Editor from '@/components/common/Editor'
import { insertBoard } from '@/api/index'
export default {
	components: {
		Editor,
	},
	data: () => ({
		rules: [
			value => !!value || 'Required.',
			value => (value && value.length >= 3) || 'Min 3 characters',
		],
		title: '',
	}),
	methods: {
		async save() {
			var content = this.$refs.editor.getContent()
			var title = this.title

			let res = await this.confirmDialog(
				'Confirm Write',
				'Do you want to save it?',
			)
			if (res) {
				insertBoard({
					params: {
						title: title,
						content: content,
					},
				})
					.then(response => {
						if (response.data > 0) {
							this.$store.commit('SET_SNACKBAR', {
								show: true,
								msg: 'Insert Complete',
								color: 'success',
							})
							this.movePage('/detail?schDocNo=' + response.data)
						}
					})
					.catch(error => {
						console.log(error)
					})
			}
		},
	},
}
</script>

<style></style>
