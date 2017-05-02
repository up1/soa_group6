import moment from 'moment'

const unit = value => {
  const units = ['B', 'KiB', 'MiB', 'GiB']
  for (let unit of units) {
    if (value / 1024 < 1) {
      return `${value.toFixed(2)} ${unit}`
    }
    value /= 1024
  }
}

const date = value => {
  return moment(value).format('D MMM YYYY')
}

export { unit, date }
