<template>
	<v-container>
		<v-card elevation="10" outlined width="100%" class="mx-auto">
			<v-card-title>
				Board
			</v-card-title>
			<v-card-text>
				<v-row>
					<v-col cols="10">
						<v-text-field
							v-model="search"
							append-icon="mdi-magnify"
							label="Search"
							single-line
							hide-details
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
							@dblclick:row="onClickRow"
							:headers="headers"
							:items="document"
							:search="search"
						>
							<template slot="items" slot-scope="props">
								<td>{{ props.item.docNo }}</td>
								<td>{{ props.item.title }}</td>
								<td>{{ props.item.writer }}</td>
								<td>{{ props.item.regDttm }}</td>
								<td>{{ props.item.view }}</td>
								<td>{{ props.item.reply }}</td>
							</template>
							<v-alert slot="no-results" :value="true" color="warning">
								Your search for "{{ search }}" found no results
							</v-alert>
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
			search: '',
			headers: [
				{ text: 'DocNo', align: 'center', value: 'docNo' },
				{ text: 'Title', align: 'start', value: 'title' },
				{ text: 'Writer', align: 'center', value: 'writer' },
				{ text: 'Register Time', align: 'center', value: 'regDttm' },
				{ text: 'View', align: 'center', value: 'view' },
				{ text: 'Reply', align: 'center', value: 'reply' },
			],
			document: [],
		}
	},
	mounted() {
		getBoardList()
			.then(response => {
				this.document = response.data
			})
			.catch(error => {
				console.log(error)
			})
	},
	methods: {
		onClickRow(event, data) {
			this.movePage('/detail?schDocNo=' + data.item.docNo)
		},
	},
}
</script>

<style></style>
