Owlpad API Implementation
=========================

<b>Index API:</b>
==================

| Resource      | Method        | Content-Type      |
| :-------------|:--------------|:------------------|
| /api/v1/index | POST          | application/json  |

Request Body:

| Parameter          | Type       | Required |   Description                |
| :------------------|:-----------|:---------|:-----------------------------|
| *directoryToIndex* | String     | Yes      | top level directory to index |
| *suffix*           | String     | No       |the file type                 |


<b>Search API:</b>
==================

| Resource       | Method        | Content-Type      |
| :--------------|:--------------|:------------------|
| /api/v1/search | POST          | application/json  |

Request Body:

| Parameter         | Type       | Required |   Description                  |
| :-----------------|:-----------|:---------|:-------------------------------|
| *keyWord*         | String     |Yes       | the key word to find           |
| *hitsPerPage*     | String     |Yes       | number of results per page     |


| Resource       | Method       | Content-Type     |
| :------------- |:-------------|------------------|
| /api/v1/search | GET          | application/json |

Request Body:

| Parameter         | Type       | Required |   Description         |
| ------------------|:-----------|:---------|:----------------------|
| *docId*           | String     | Yes      |the id of the document |

