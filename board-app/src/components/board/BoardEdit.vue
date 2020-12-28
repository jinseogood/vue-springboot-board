<template>
	<v-container>
		<v-card elevation="10" outlined width="100%" class="mx-auto">
			<v-card-title>
				<span class="mr-2">Edit</span>
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
					@click="edit"
					color="warning"
					class="ma-2 white--text"
					rounded
					small
				>
					<v-icon small>
						mdi-pencil
					</v-icon>
					<span style="width:5px;"></span>
					Edit
				</v-btn>
				<v-btn
					@click="movePage('/detail?schDocNo=' + docNo)"
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
import { getBoardDetail, updateBoard } from '@/api/index'
export default {
	components: {
		Editor,
	},
	data: () => ({
		rules: [
			value => !!value || 'Required.',
			value => (value && value.length >= 3) || 'Min 3 characters',
		],
		docNo: '',
		title: '',
		writer: '',
	}),
	mounted() {
		getBoardDetail({
			params: {
				schDocNo: this.$route.query.schDocNo,
			},
		})
			.then(response => {
				this.docNo = response.data.docNo
				this.title = response.data.title
				this.$refs.editor.setContent(response.data.content)
				// this.writer = response.data.writer
			})
			.catch(error => {
				console.log(error)
			})
	},
	methods: {
		async edit() {
			var docNo = this.docNo
			var title = this.title
			var content = this.$refs.editor.getContent()

			let res = await this.confirmDialog(
				'Confirm Edit',
				'Do you want to save it?',
			)
			if (res) {
				updateBoard({
					params: {
						docNo: docNo,
						title: title,
						content: content,
					},
				})
					.then(response => {
						if (response.data > 0) {
							this.$store.commit('SET_SNACKBAR', {
								show: true,
								msg: 'Edit Complete',
								color: 'warning',
							})
							this.movePage('/detail?schDocNo=' + docNo)
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
