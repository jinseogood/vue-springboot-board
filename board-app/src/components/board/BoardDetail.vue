<template>
	<v-container>
		<v-card elevation="10" outlined width="100%" class="mx-auto">
			<v-card-title>
				<span class="mr-2">Detail</span>
			</v-card-title>
			<v-card-text>
				<v-row>
					<v-col cols="9">
						<v-text-field readonly v-model="title" label="Title"></v-text-field>
					</v-col>
					<v-col cols="1" align-self="center">
						<v-tooltip bottom>
							<template v-slot:activator="{ on, attrs }">
								<v-icon v-on="on" v-bind="attrs">
									mdi-account
								</v-icon>
							</template>
							<span>Writer<br />{{ writer }}</span>
						</v-tooltip>
					</v-col>
					<v-col cols="1" align-self="center">
						<v-tooltip bottom>
							<template v-slot:activator="{ on, attrs }">
								<v-icon v-on="on" v-bind="attrs">
									mdi-clock-outline
								</v-icon>
							</template>
							<span>Register Time<br />{{ regDttm }}</span>
						</v-tooltip>
					</v-col>
					<v-col cols="1" align-self="center">
						<v-tooltip bottom>
							<template v-slot:activator="{ on, attrs }">
								<v-icon v-on="on" v-bind="attrs">
									mdi-eye
								</v-icon>
							</template>
							<span>View<br />{{ view }}</span>
						</v-tooltip>
					</v-col>
				</v-row>
				Content<br />
				<Viewer ref="viewer" />
			</v-card-text>
			<v-card-actions>
				<v-spacer></v-spacer>
				<v-btn
					@click="movePage('/edit?schDocNo=' + docNo)"
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
					@click="del"
					color="error"
					class="ma-2 white--text"
					rounded
					small
				>
					<v-icon small>
						mdi-delete-forever
					</v-icon>
					<span style="width:5px;"></span>
					Delete
				</v-btn>
				<v-btn
					@click="movePage('/list')"
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
import Viewer from '@/components/common/Viewer'
import { getBoardDetail, deleteBoard } from '@/api/index'

export default {
	components: {
		Viewer,
	},
	data() {
		return {
			docNo: 0,
			title: '',
			writer: '',
			regDttm: '',
			view: 0,
			reply: 0,
		}
	},
	mounted() {
		getBoardDetail({
			params: {
				schDocNo: this.$route.query.schDocNo,
			},
		})
			.then(response => {
				this.docNo = response.data.docNo
				this.title = response.data.title
				this.$refs.viewer.setContent(response.data.content)
				this.writer = response.data.writer
				this.regDttm = response.data.regDttm
				this.view = response.data.view
				this.reply = response.data.reply
			})
			.catch(error => {
				console.log(error)
			})
	},
	methods: {
		async del() {
			let res = await this.confirmDialog(
				'Confirm Delete',
				'Are you sure you want to delete it?',
			)
			if (res) {
				deleteBoard({
					params: {
						docNo: this.docNo,
					},
				})
					.then(response => {
						if (response.data > 0) {
							this.$store.commit('SET_SNACKBAR', {
								show: true,
								msg: 'Delete Complete',
								color: 'error',
							})
							this.movePage('/list')
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
