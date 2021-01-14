<template>
	<v-container>
		<v-card elevation="10" outlined width="100%" class="mx-auto">
			<v-card-title>
				<span class="mr-2">Detail</span>
			</v-card-title>
			<v-card-text>
				<v-row>
					<v-col>
						<v-text-field label="Title" readonly :value="title" />
					</v-col>
				</v-row>
				<v-row>
					<v-col>
						<v-text-field label="Writer" readonly dense :value="writer" />
					</v-col>
					<v-col>
						<v-text-field
							label="Register Time"
							readonly
							dense
							:value="regDttm"
						/>
					</v-col>
					<v-col>
						<v-text-field label="View" readonly dense :value="view" />
					</v-col>
				</v-row>
				Content<br />
				<div style="height:300px;"><Viewer ref="viewer" /><br /></div>
				Reply ({{ replyCount }})<br />
				<v-simple-table dense>
					<tbody>
						<tr v-for="(reply, index) in replies" :key="index">
							<td style="width:110px; padding: 0;" v-if="!isMobile()">
								<v-icon small>
									mdi-account
								</v-icon>
								{{ reply.writer }}
							</td>
							<td style="width:40px; padding: 0;" v-else>
								<Tooltip
									bottom
									iconName="mdi-account"
									title="작성자"
									:content="reply.writer"
								/>
							</td>
							<td style="padding: 0;">{{ reply.content }}</td>
							<td style="width:140px; padding: 0;" v-if="!isMobile()">
								{{ reply.regDttm }}
							</td>
							<td style="width:40px; padding: 0;" v-else>
								<Tooltip
									bottom
									iconName="mdi-clock-outline"
									title="작성일시"
									:content="reply.regDttm"
								/>
							</td>
							<td style="width:30px; padding: 0;">
								<Button
									@click="replyEdit(reply.replyNo, reply.content)"
									color="grey"
									icon
									xsmall
									iconName="mdi-pencil"
								/>
							</td>
							<td style="width:30px; padding: 0;">
								<Button
									@click="replyDel(reply.replyNo)"
									color="red"
									icon
									xsmall
									iconName="mdi-close"
								/>
							</td>
						</tr>
					</tbody>
				</v-simple-table>
				<v-divider></v-divider>
				<v-row>
					<v-col cols="12" md="11" style="padding: 0px 12px;">
						<v-textarea
							clearable
							clear-icon="mdi-close-circle"
							rows="2"
							no-resize
							full-width
							v-model="comment"
						></v-textarea>
					</v-col>
					<v-col md="1" align-self="center" style="padding: 0px 10px;">
						<Button
							@click="replySave"
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
					@click="movePage('/edit?docNo=' + docNo)"
					color="warning"
					rounded
					small
					iconName="mdi-pencil"
					btnName="Edit"
				></Button>
				<Button
					@click="del"
					color="error"
					rounded
					small
					iconName="mdi-delete-forever"
					btnName="Delete"
				></Button>
				<Button
					@click="movePage('/list')"
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
import Tooltip from '@/components/common/Tooltip'
import btnMixins from '@/mixins/btnMixins'
import {
	getBoardDetailAPI,
	deleteBoardAPI,
	insertReplyAPI,
	getReplyListAPI,
	deleteReplyAPI,
	updateReplyAPI,
} from '@/api/index'

export default {
	mixins: [btnMixins],
	components: {
		Viewer,
		Tooltip,
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
		getBoardDetailAPI({
			params: {
				docNo: this.$route.query.docNo,
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
		getReplyListAPI({
			params: {
				docNo: this.$route.query.docNo,
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
				deleteBoardAPI({
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
				insertReplyAPI({
					params: {
						docNo: this.docNo,
						comment: this.comment,
					},
				})
					.then(response => {
						if (response.data > 0) {
							this.refresh()
						}
					})
					.catch(error => {
						console.log(error)
					})
			}
		},
		async replyEdit(replyNo, comment) {
			let res = await this.promptDialog('Edit Reply', 'Comment', comment)
			if (res) {
				updateReplyAPI({
					params: {
						replyNo: replyNo,
						docNo: this.docNo,
						comment: res,
					},
				})
					.then(response => {
						if (response.data > 0) {
							this.refresh()
						}
					})
					.catch(error => {
						console.log(error)
					})
			}
		},
		async replyDel(replyNo) {
			let res = await this.confirmDialog(
				'Confirm Reply Delete',
				'Are you sure you want to delete it?',
			)
			if (res) {
				deleteReplyAPI({
					params: {
						replyNo: replyNo,
						docNo: this.docNo,
					},
				})
					.then(response => {
						if (response.data > 0) {
							this.refresh()
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
