import ApiBook from 'api-book'

const headers = {
  Accept: 'application/json'
}

const options = {
  credentials: 'include'
}

const book = {
  login: {
    url: '/api/v1/login',
    method: 'POST',
    headers,
    options
  },
  getUserInfo: {
    url: '/api/v1/customers',
    method: 'GET',
    headers,
    options,
    payload: {
      query: {
        email: '{{email}}',
        code: '{{code}}'
      }
    }
  },
  redeem: {
    url: '/api/v1/compensations/codes/{{compensationCode}}/redemptions',
    method: 'POST',
    headers,
    options
  }

}

const Apis = new ApiBook.ApiCreator(book)

export default Apis
