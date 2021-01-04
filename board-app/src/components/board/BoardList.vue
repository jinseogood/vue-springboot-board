<template>
	<v-container>
		<v-card elevation="10" outlined width="100%" class="mx-auto">
			<v-card-title>
				Board
			</v-card-title>
			<v-card-text>
				<v-row>
					<v-col cols="2" align-self="end" style="padding: 30px 12px 0 12px;">
						<v-select
							v-model="schType"
							label="Condition"
							dense
							:items="conditions"
						></v-select>
					</v-col>
					<v-col cols="8">
						<v-text-field
							v-model="schVal"
							append-icon="mdi-magnify"
							label="Search"
							single-line
							hide-details
							@keypress.enter.prevent="onKeyPressText"
						></v-text-field>
					</v-col>
					<v-col cols="2" align-self="center">
						<Button
							@click.native="movePage('/write')"
							color="black"
							rounded
							small
							iconName="mdi-pencil"
							btnName="Write"
						></Button>
					</v-col>
				</v-row>
				<v-row>
					<v-col>
						<v-data-table
							class="elevation-1"
							@dblclick:row="onClickRow"
							:headers="headers"
							:items="document"
							:options.sync="options"
							:server-items-length="totalCount"
							:footer-props="footerOptions"
							:loading="loading"
						>
							<template slot="items" slot-scope="props">
								<td>{{ props.item.docNo }}</td>
								<td>{{ props.item.title }}</td>
								<td>{{ props.item.writer }}</td>
								<td>{{ props.item.regDttm }}</td>
								<td>{{ props.item.view }}</td>
								<td>{{ props.item.reply }}</td>
							</template>
						</v-data-table>
					</v-col>
				</v-row>
			</v-card-text>
		</v-card>
	</v-container>
</template>

<script>
import { getBoardList } from '@/api/index'

export default {
	data() {
		return {
			headers: [
				{ text: 'DocNo', align: 'center', value: 'docNo' },
				{ text: 'Title', align: 'start', value: 'title' },
				{ text: 'Writer', align: 'center', value: 'writer' },
				{ text: 'Register Time', align: 'center', value: 'regDttm' },
				{ text: 'View', align: 'center', value: 'view' },
				{ text: 'Reply', align: 'center', value: 'reply' },
			],
			document: [],
			options: {
				multiSort: true,
				sortBy: ['docNo'],
				sortDesc: [true],
				page: 1,
				itemsPerPage: 5,
			},
			footerOptions: {
				'items-per-page-options': [5, 10, 25, 50, 100],
			},
			totalCount: 0,
			loading: false,
			conditions: [
				{ text: 'DocNo', value: 'docNo' },
				{ text: 'Title', value: 'title' },
				{ text: 'Writer', value: 'writer' },
			],
			schType: '',
			schVal: '',
		}
	},
	mounted() {
		// this.getBoardList()
	},
	watch: {
		options: {
			handler() {
				this.getBoardList().then(data => {
					this.document = data.items
					this.totalCount = data.total
				})
			},
			deep: true,
		},
	},
	methods: {
		getBoardDataFromAPI(page, itemsPerPage, sort) {
			return getBoardList({
				params: {
					schType: this.schType,
					schVal: this.schVal,
					page: page,
					rows: itemsPerPage,
					sort: encodeURIComponent(sort),
				},
			})
				.then(response => {
					return response.data
				})
				.catch(error => {
					console.log(error)
				})
		},
		getBoardList() {
			const vm = this
			this.loading = true
			// eslint-disable-next-line
			return new Promise((resolve, reject) => {
				const { sortBy, sortDesc, page, itemsPerPage } = this.options
				let sort = []

				if (sortBy.length > 0) {
					// eslint-disable-next-line
					sortBy.forEach((value, index) => {
						sort.push(
							value
								.replace(/[A-Z]/g, function(str) {
									return '_' + str[0]
								})
								.toUpperCase() +
								' ' +
								(sortDesc[index] ? 'desc' : 'asc'),
						)
					})
				} else {
					sort.push('DOC_NO desc')
				}

				let items = this.getBoardDataFromAPI(page, itemsPerPage, sort).then(
					response => {
						items = response.data
						const total = response.total

						setTimeout(() => {
							vm.loading = false
							resolve({
								items,
								total,
							})
						}, 1000)
					},
				)
			})
		},
		onClickRow(event, data) {
			this.movePage('/detail?docNo=' + data.item.docNo)
		},
		onKeyPressText() {
			this.getBoardList()
		},
	},
}
</script>

<style></style>
