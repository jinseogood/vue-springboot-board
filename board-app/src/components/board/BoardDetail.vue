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
				<Viewer ref="viewer" /><br />
				Reply ({{ replyCount }})<br />
				<v-simple-table dense>
					<tbody>
						<tr v-for="(reply, index) in replies" :key="index">
							<td style="width:100px; padding: 0;">
								<v-icon small>
									mdi-account
								</v-icon>
								{{ reply.writer }}
							</td>
							<td style="padding: 0;">{{ reply.content }}</td>
							<td style="width:150px; padding: 0;">{{ reply.regDttm }}</td>
						</tr>
					</tbody>
				</v-simple-table>
				<v-divider></v-divider>
				<v-row>
					<v-col style="padding: 0px 12px;">
						<v-textarea
							clearable
							clear-icon="mdi-close-circle"
							rows="2"
							no-resize
							v-model="comment"
						></v-textarea>
					</v-col>
					<v-col cols="2" align-self="center">
						<Button
							@click.native="replySave"
							color="indigo"
							rounded
							small
							iconName="mdi-pencil"
							btnName="Save"
						></Button>
					</v-col>
				</v-row>
			</v-card-text>
			<v-card-actions>
				<v-spacer></v-spacer>
				<Button
					@click.native="movePage('/edit?schDocNo=' + docNo)"
					color="warning"
					rounded
					small
					iconName="mdi-pencil"
					btnName="Edit"
				></Button>
				<Button
					@click.native="del"
					color="error"
					rounded
					small
					iconName="mdi-delete-forever"
					btnName="Delete"
				></Button>
				<Button
					@click.native="movePage('/list')"
					color="grey darken-1"
					rounded
					small
					iconName="mdi-arrow-left"
					btnName="Back"
				></Button>
			</v-card-actions>
		</v-card>
	</v-container>
</template>

<script>
import Viewer from '@/components/common/Viewer'
import {
	getBoardDetail,
	deleteBoard,
	insertReply,
	getReplyList,
} from '@/api/index'

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
			replyCount: 0,
			replies: null,
			comment: '',
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
				this.replyCount = response.data.reply
			})
			.catch(error => {
				console.log(error)
			})
		getReplyList({
			params: {
				schDocNo: this.$route.query.schDocNo,
			},
		})
			.then(response => {
				this.replies = response.data
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
		replySave() {
			if (this.comment !== null) {
				insertReply({
					params: {
						docNo: this.docNo,
						comment: this.comment,
					},
				})
					.then(response => {
						if (response.data > 0) {
							this.$router.go(this.$router.currentRoute)
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
