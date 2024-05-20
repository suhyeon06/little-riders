interface Props {
  children: React.ReactNode
}

export default function PendingListTable({ children }: Props) {
  return <table className="w-full text-center">{children}</table>
}
